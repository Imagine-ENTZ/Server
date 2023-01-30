package com.imaginecup.entz.controller;

import com.imaginecup.entz.domain.Token;
import com.imaginecup.entz.service.TokenService;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController // JSON 형태 결과값을 반환해줌 (@ResponseBody가 필요없음)
@RequiredArgsConstructor // final 객체를 Constructor Injection 해줌. (Autowired 역할)
@RequestMapping(value = "/token", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TokenController {

    private final TokenService tokenService;

    // 토큰 가져오기
    @GetMapping("")
    public Optional<Token> findToken() {
        Optional<Token> token = tokenService.findByToken();
        return token;
    }

    // 새로 발급된 토큰 가져오기
    @PostMapping("/{date}")
    public Map<String, Object> findNewToken(@PathVariable("date") Long date) throws ParseException {
        Optional<Token> token = tokenService.findNewToken(date);
        Map<String, Object> response = new HashMap<>();
        if(token != null) {
            response.put("result", "SUCCESS");
            response.put("user", token);
        } else {
            response.put("result", "FAIL");
            response.put("reason", "변경을 하지 못하였습니다");
        }
        return response;
    }





}
