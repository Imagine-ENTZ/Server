package com.imaginecup.entz.service;

import com.imaginecup.entz.domain.Member;
import com.imaginecup.entz.domain.Peer;
import com.imaginecup.entz.repository.MemberRepository;
import com.imaginecup.entz.repository.PeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PeerService {

    private final PeerRepository peerRepository;

    @Autowired
    public PeerService(PeerRepository peerRepository) {
        this.peerRepository = peerRepository;
    }

    // id로 조회
    public Optional<Peer> findById(Long id) {
        return peerRepository.findById(id);
    }


}
