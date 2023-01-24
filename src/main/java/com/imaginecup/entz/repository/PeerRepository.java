package com.imaginecup.entz.repository;

import com.imaginecup.entz.domain.Member;
import com.imaginecup.entz.domain.Peer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeerRepository extends JpaRepository<Peer, Long> {
    Optional<Peer> findById(Long id);
}
