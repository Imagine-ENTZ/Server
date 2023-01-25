package com.imaginecup.entz.service;

import com.imaginecup.entz.domain.Member;
import com.imaginecup.entz.domain.Token;
import com.imaginecup.entz.repository.TokenRepository;
import com.imaginecup.entz.utils.Constants;
import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.Date;
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


    public void updateToken(String uptToken) {
        Optional<Token> token = findByToken();
        token.ifPresent(t-> {
            t.setToken(uptToken);
            tokenRepository.save(t);
        });
    }

    // * * * * * * 1초
    // 10 * * * * * 1분
    // 0 0 01 * * * 1시간
    @Scheduled(cron = "0 0 01 * * *")
    public void run() throws ParseException {
        System.out.println("현재 시간은 " + new Date());

        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .build();

        JSONObject productInfo = new JSONObject();

        productInfo.put("apikey", Constants.TOKEN);


        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(client.post()
                .uri("https://api.flaticon.com/v3/app/authentication")
                .bodyValue(productInfo)
                .retrieve()
                .bodyToMono(String.class)
                .block());

        JSONObject data = (JSONObject)  jsonObject.get("data");
        //data.get("token");

        updateToken(data.get("token").toString());
        System.out.println(data.get("token").toString()); // apple

    }

}
