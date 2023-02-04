package com.imaginecup.entz.service.implement;

import com.imaginecup.entz.domain.Gameroom;
import com.imaginecup.entz.repository.GameroomRepository;
import com.imaginecup.entz.service.GameroomService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GameroomServiceImp implements GameroomService {

    private final GameroomRepository gameroomRepository;

    public GameroomServiceImp(GameroomRepository gameroomRepository) {
        this.gameroomRepository = gameroomRepository;
    }

    // 참가여부 확인
    @Override
    @Transactional
    public Gameroom findByCode(Long code) {return gameroomRepository.findByCode(code);}


    // 참가여부 변경
    @Override
    @Transactional
    public boolean setFull(Long code) {

        Gameroom room = gameroomRepository.findByCode(code);

        if(room != null){
            if( room.getFull() == 0)
                room.setFull(1L);
            else
                room.setFull(0L);
            return true;
        }
        return false;

    }

    // 모든 방 조회
    @Override
    @Transactional
    public List<Gameroom> getRoomList() {
        return gameroomRepository.findAll();
    }

    // 등록
    @Override
    @Transactional
    public Gameroom save(Gameroom value) {
        Gameroom room = Gameroom.builder()
                .code(value.getCode())
                .name(value.getName())
                .full(value.getFull())
                .type(value.getType()).build();
        return gameroomRepository.save(room);
    }

    // 삭제
    @Override
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
