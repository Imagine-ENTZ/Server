package com.imaginecup.entz.controller;

import com.imaginecup.entz.domain.Gameroom;
import com.imaginecup.entz.domain.Member;
import com.imaginecup.entz.service.GameroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // JSON 형태 결과값을 반환해줌 (@ResponseBody가 필요없음)
@RequiredArgsConstructor // final 객체를 Constructor Injection 해줌. (Autowired 역할)
@RequestMapping(value = "/room", produces = {MediaType.APPLICATION_JSON_VALUE})// version1의 API
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GameroomController {

    private final GameroomService gameroomService;


    // 게임방 모두 조회
    // id로 유저검색
    @GetMapping("")
    public Map<String, Object> findById() {
        Map<String, Object> response = new HashMap<>();
        List<Gameroom> gameroomList = gameroomService.getRoomList();

        response.put("result", "SUCCESS");
        response.put("room", gameroomList);

        return response;
    }

    // 게임방 등록
    @PostMapping("")
    public Map<String, Object> save(@RequestBody Gameroom value) {
        Map<String, Object> response = new HashMap<>();

        Gameroom room = gameroomService.save(value);
        if(room != null) {
            response.put("result", "SUCCESS");
            response.put("room", room);
        } else {
            response.put("result", "FAIL");
            response.put("reason", "방 등록 실패");
        }
        return response;
    }

    // 게임방 삭제
    @DeleteMapping("")
    public Map<String, Object> delete(@RequestBody Gameroom value) {
        Map<String, Object> response = new HashMap<>();

        if(gameroomService.delete(value.getCode()) > 0) {
            response.put("result", "SUCCESS");
        } else {
            response.put("result", "FAIL");
        }
        return response;
    }
}
