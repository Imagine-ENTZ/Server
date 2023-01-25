package com.imaginecup.entz.controller;

import com.imaginecup.entz.domain.Token;
import com.imaginecup.entz.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController // JSON 형태 결과값을 반환해줌 (@ResponseBody가 필요없음)
@RequiredArgsConstructor // final 객체를 Constructor Injection 해줌. (Autowired 역할)
@RequestMapping(value = "/token", produces = {MediaType.APPLICATION_JSON_VALUE})
public class TokenController {

    private final TokenService tokenService;
    @GetMapping("")
    public Optional<Token> findById() {
        Optional<Token> token = tokenService.findByToken(1);

        return token;
    }

}
