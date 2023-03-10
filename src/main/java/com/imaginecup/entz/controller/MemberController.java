package com.imaginecup.entz.controller;

import com.imaginecup.entz.domain.Member;
import com.imaginecup.entz.domain.Peer;
import com.imaginecup.entz.repository.MemberRepository;
import com.imaginecup.entz.service.MemberService;
import com.imaginecup.entz.service.implement.MemberServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController // JSON 형태 결과값을 반환해줌 (@ResponseBody가 필요없음)
@RequiredArgsConstructor // final 객체를 Constructor Injection 해줌. (Autowired 역할)
@RequestMapping(value = "/member", produces = {MediaType.APPLICATION_JSON_VALUE})// version1의 API
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MemberController {

    private final MemberServiceImp memberServiceImp;

    // id로 유저검색
    @GetMapping("/{id}")
    public Map<String, Object> findById(@PathVariable("id") String id) {
        Map<String, Object> response = new HashMap<>();
        Member oMember = memberServiceImp.findByUser(id);
        if(memberServiceImp.existsByUser(id)) {
            response.put("result", "SUCCESS");
            response.put("user", oMember.getUser());
        } else {
            response.put("result", "FAIL");
            response.put("reason", "일치하는 회원 정보가 없습니다. 사용자 id를 확인해주세요.");
        }
        return response;
    }
    // 로그인
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Member value) {
        Map<String, Object> response = new HashMap<>();

        boolean oMember = memberServiceImp.login(value);
        if(oMember == true) {
            response.put("result", "SUCCESS");
            response.put("user", value.getUser());
        } else {
            response.put("result", "FAIL");
            response.put("reason", "일치하는 회원 정보가 없습니다. 사용자 id를 확인해주세요.");
        }
        return response;
    }

   // 회원가입
    @PostMapping("")
    public Map<String, Object> save(@RequestBody Member value) {
        Map<String, Object> response = new HashMap<>();

        Member user = memberServiceImp.save(value);
        if(user != null) {
            response.put("result", "SUCCESS");
            response.put("user", user);
        } else {
            response.put("result", "FAIL");
            response.put("reason", "회원 가입 실패");
        }

        return response;
    }

    // 회원삭제
    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@RequestBody Member value) {
        Map<String, Object> response = new HashMap<>();

        if(memberServiceImp.delete(value.getUser()) > 0) {
            response.put("result", "SUCCESS");
        } else {
            response.put("result", "FAIL");
            response.put("reason", "일치하는 회원 정보가 없습니다. 사용자 id를 확인해주세요.");
        }
        return response;
    }
}


