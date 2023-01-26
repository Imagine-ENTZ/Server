package com.imaginecup.entz.repository;

import com.imaginecup.entz.domain.Gameroom;
import com.imaginecup.entz.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GameroomRepository extends JpaRepository<Gameroom, Long> {


    Gameroom findByCode(Long code);
}
