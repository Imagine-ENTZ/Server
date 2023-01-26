package com.imaginecup.entz.service;


import com.imaginecup.entz.domain.Gameroom;
import com.imaginecup.entz.repository.GameroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GameroomService {

    private final GameroomRepository gameroomRepository;

    @Autowired
    public GameroomService(GameroomRepository gameroomRepository) {
        this.gameroomRepository = gameroomRepository;
    }


    // 참여여부 확인
    @Transactional
    public boolean find(Long code) {
        Gameroom room = gameroomRepository.findByCode(code);

        if (room.getFull() == 1)
            return false;
        else
            return true;
    }

    // 모든 방 조회
    @Transactional
    public List<Gameroom> getRoomList() {
        return gameroomRepository.findAll();
    }


    // 등록
    @Transactional
    public Gameroom save(Gameroom value) {
        Gameroom room = Gameroom.builder()
                .code(value.getCode())
                .name(value.getName())
                .type(value.getType()).build();
        return gameroomRepository.save(room);
    }

    // 삭제
    @Transactional
    public int delete(Long code) {
        Optional<Gameroom> room = gameroomRepository.findById(code);
        if(room.isPresent()) {
            gameroomRepository.delete(room.get());
            return 1;
        }
        return 0;
    }


}
