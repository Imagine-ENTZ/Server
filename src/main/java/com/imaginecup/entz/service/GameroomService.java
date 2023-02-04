package com.imaginecup.entz.service;


import com.imaginecup.entz.domain.Gameroom;
import java.util.List;

public interface GameroomService {

    Gameroom findByCode(Long code);
    boolean setFull(Long code) ;

    List<Gameroom> getRoomList();

    Gameroom save(Gameroom value);
    int delete(Long code);

}
