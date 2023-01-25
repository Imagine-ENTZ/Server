package com.imaginecup.entz.service;

import com.imaginecup.entz.domain.Token;
import com.imaginecup.entz.repository.TokenRepository;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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

    // * * * * * * 1초
    // 10 * * * * * 1분
    // 0 0 01 * * * 1시간
    @Scheduled(cron = "* * * * * *")
    public void run() {
        System.out.println("현재 시간은 " + new Date());

        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:8080")

                .build();


        JSONObject productInfo = new JSONObject();

        productInfo.put("apikey", "YQLlNmLtavqzq6SPO572wOOm6axxQONcD9UxwrJIlXVl4N7T");



        System.out.println( client.post()
                .uri("https://api.flaticon.com/v3/app/authentication")
                .bodyValue(productInfo)
                .retrieve()
                .bodyToMono(String.class)
                .block());
    }

}
