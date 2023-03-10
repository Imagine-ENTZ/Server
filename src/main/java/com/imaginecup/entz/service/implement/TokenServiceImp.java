package com.imaginecup.entz.service.implement;

import com.imaginecup.entz.domain.Token;
import com.imaginecup.entz.repository.TokenRepository;
import com.imaginecup.entz.service.TokenService;
import com.imaginecup.entz.utils.Constants;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
@EnableScheduling
public class TokenServiceImp implements TokenService {

    private final TokenRepository tokenRepository;

    public TokenServiceImp(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Optional<Token> findByToken() {
        return tokenRepository.findById(1);
    }

    // 등록
    @Override
    @Transactional
    public Optional<Token> findNewToken(Long date) throws ParseException {
        Optional<Token> token= findByToken();

        System.out.println(date + " " +token.get().getDate());
        if(date - token.get().getDate() > 3600) {

            String newToken = getNewToken();
            token.get().setToken(newToken);
            token.get().setDate(date);
            updateToken(newToken,date);

            return token;
        }
        else
            return null;
    }

    // 새로운토큰 발급
    @Override
    public String getNewToken() throws ParseException {
        WebClient client = WebClient.builder()
                .baseUrl(Constants.AZURE_SERVER)
                .build();

        JSONObject productInfo = new JSONObject();

        //api를 이용해서 토큰 발급
        productInfo.put("apikey", Constants.FLATICON_API);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(client.post()
                .uri(Constants.FLATICON_URL)
                .bodyValue(productInfo)
                .retrieve()
                .bodyToMono(String.class)
                .block());

        JSONObject data = (JSONObject)  jsonObject.get("data");
        return data.get("token").toString();
    }

    // 기존토큰 업데이트
    @Override
    public void updateToken(String uptToken, Long date) {
        Optional<Token> token = findByToken();
        token.ifPresent(t-> {
            t.setToken(uptToken);
            t.setDate((date));
            tokenRepository.save(t);
        });
    }


    // * * * * * * 1초
    // 10 * * * * * 1분
    // 0 0 01 * * * 1시간
    // 1시간 마다 토큰 새로 발급해서 DB에 저장
    @Override
    @Scheduled(cron = "0 0 01 * * *")
    public void run() throws ParseException {
        System.out.println("현재 시간은 " + new Date());

        WebClient client = WebClient.builder()
                .baseUrl(Constants.AZURE_SERVER)
                .build();

        JSONObject productInfo = new JSONObject();

        productInfo.put("apikey", Constants.FLATICON_API);


        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(client.post()
                .uri(Constants.FLATICON_URL)
                .bodyValue(productInfo)
                .retrieve()
                .bodyToMono(String.class)
                .block());

        JSONObject data = (JSONObject)  jsonObject.get("data");

        updateToken(data.get("token").toString(), 0L);
        System.out.println(data.get("token").toString()); // apple

    }
}
