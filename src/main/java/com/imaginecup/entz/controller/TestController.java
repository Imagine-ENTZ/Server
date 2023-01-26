package com.imaginecup.entz.controller;

import com.imaginecup.entz.domain.Member;
import com.imaginecup.entz.domain.Peer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import java.util.HashMap;
import java.util.Map;

@RestController // JSON 형태 결과값을 반환해줌 (@ResponseBody가 필요없음)
@RequiredArgsConstructor // final 객체를 Constructor Injection 해줌. (Autowired 역할)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TestController {
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    //pub/hello 으로 요청들어왓을때
    @MessageMapping("/play/welcome")
    public void message(Member member){
        simpMessageSendingOperations.convertAndSend("/sub/chat/" + member.getUser()  , member);
    }
    @MessageMapping("/play/offer")
    public void sendOffer(Member member){
        simpMessageSendingOperations.convertAndSend("/sub/chat/" + member.getUser()  , member);
    }

    @MessageMapping("/play")
    @ResponseBody
    public void getEnter(@RequestBody Peer peer) {


        System.out.println(" 지금 들어온 결과값 :" + peer.getType());


        simpMessageSendingOperations.convertAndSend("/sub/play/" + peer.getRoom_id() , peer);
    }



}

