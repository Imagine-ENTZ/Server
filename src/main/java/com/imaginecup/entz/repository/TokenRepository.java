package com.imaginecup.entz.repository;

import com.imaginecup.entz.domain.Member;
import com.imaginecup.entz.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {


}
