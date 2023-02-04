package com.imaginecup.entz.controller;

import com.imaginecup.entz.domain.Member;
import com.imaginecup.entz.domain.Peer;
import com.imaginecup.entz.service.MemberService;
import com.imaginecup.entz.service.PeerService;
import com.imaginecup.entz.service.implement.PeerServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController // JSON 형태 결과값을 반환해줌 (@ResponseBody가 필요없음)
@RequiredArgsConstructor // final 객체를 Constructor Injection 해줌. (Autowired 역할)
@RequestMapping(value = "/peer", produces = {MediaType.APPLICATION_JSON_VALUE})// version1의 API
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PeerController {

    private final PeerServiceImp peerServiceImp;

    @GetMapping("/{id}")
    public Map<String, Object> findById(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();

        Optional<Peer> oPeer = peerServiceImp.findById(id);
        if(oPeer.isPresent()) {
            response.put("result", "SUCCESS");
            response.put("user", oPeer.get());
        } else {
            response.put("result", "FAIL");
            response.put("reason", "일치하는 회원 정보가 없습니다. 사용자 id를 확인해주세요.");
        }
        return response;
    }
}

