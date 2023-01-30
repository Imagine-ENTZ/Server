package com.imaginecup.entz.service;

import com.imaginecup.entz.domain.Token;
import com.imaginecup.entz.repository.TokenRepository;
import com.imaginecup.entz.utils.Constants;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@EnableScheduling
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Optional<Token> findByToken() {
        return tokenRepository.findById(1);
    }

    // 등록
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
        //data.get("token");

//        updateToken(data.get("token").toString());

        return data.get("token").toString();
    }


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
//    @Scheduled(cron = "0 0 01 * * *")
//    public void run() throws ParseException {
//        System.out.println("현재 시간은 " + new Date());
//
//        WebClient client = WebClient.builder()
//                .baseUrl(Constants.AZURE_SERVER)
//                .build();
//
//        JSONObject productInfo = new JSONObject();
//
//        productInfo.put("apikey", Constants.TOKEN);
//
//
//        JSONParser parser = new JSONParser();
//        JSONObject jsonObject = (JSONObject) parser.parse(client.post()
//                .uri(Constants.FLATICON_URL)
//                .bodyValue(productInfo)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block());
//
//        JSONObject data = (JSONObject)  jsonObject.get("data");
//        //data.get("token");
//
//        updateToken(data.get("token").toString());
//        System.out.println(data.get("token").toString()); // apple
//
//    }

}
