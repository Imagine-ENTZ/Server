package com.imaginecup.entz.service;

import com.imaginecup.entz.domain.Token;
import com.imaginecup.entz.repository.TokenRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
    @Scheduled(cron = "10 * * * * *")
    public void run() {
        System.out.println("현재 시간은 " + new Date());
    }

}
