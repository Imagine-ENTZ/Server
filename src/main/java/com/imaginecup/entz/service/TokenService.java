package com.imaginecup.entz.service;

import com.imaginecup.entz.domain.Token;
import com.nimbusds.jose.shaded.json.parser.ParseException;

import java.util.Optional;


public interface TokenService {

    Optional<Token> findByToken();

    // 등록
    Optional<Token> findNewToken(Long date) throws ParseException;

    // 새로운 토큰 발급
    String getNewToken() throws ParseException;

    // 발급된 토큰 업데이트
    void updateToken(String uptToken, Long date);

    // 1시간마다 자동으로 토큰 새로 발급
    void run() throws ParseException;

}
