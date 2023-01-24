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
public class TestController {
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    //pub/hello 으로 요청들어왓을때
    @MessageMapping("/gameroom/welcome")
    public void message(Member member){
        simpMessageSendingOperations.convertAndSend("/sub/chat/" + member.getId()  , member);
    }
    @MessageMapping("/gameroom/offer")
    public void sendOffer(Member member){
        simpMessageSendingOperations.convertAndSend("/sub/chat/" + member.getId()  , member);
    }

    @MessageMapping("/gameroom/123")
    @ResponseBody
    public void getEnter(@RequestBody Peer peer) {

//        Peer peerContent;
//
//        peerContent = Peer.builder()
//                .id(0L)
//                .type(peer.getType())
//                .room_id(peer.getRoom_id())
//                .sender(peer.getSender())
//                .offer(peer.getOffer())
//                .answer(peer.getAnswer())
//                .build();
//
//        System.out.println(peerContent.toString());
        System.out.println(" 지금 들어온 결과값 :" + peer.getType());

        simpMessageSendingOperations.convertAndSend("/sub/gameroom/123" , peer);
//        simpMessageSendingOperations.convertAndSend("/sub/chat/" + member.getId()  , member);
    }

    //client가 send 경로(setApplicationDestinationPrefixes)
    //"/pub/chat/enter" : 회원 입장 -> "/sub/chat/department/{departmentId}"로 채팅방에 참여한 회원 이메일 전송


}

