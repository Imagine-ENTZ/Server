package com.imaginecup.entz.service;

import com.imaginecup.entz.domain.Token;
import com.imaginecup.entz.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Optional<Token> findByToken(int i) {
        return tokenRepository.findById(1);
    }
}
