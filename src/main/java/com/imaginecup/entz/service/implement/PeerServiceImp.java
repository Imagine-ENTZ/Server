package com.imaginecup.entz.service.implement;

import com.imaginecup.entz.domain.Peer;
import com.imaginecup.entz.repository.PeerRepository;
import com.imaginecup.entz.service.PeerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PeerServiceImp implements PeerService {

    private final PeerRepository peerRepository;

    public PeerServiceImp(PeerRepository peerRepository) {
        this.peerRepository = peerRepository;
    }
    
    // id로 조회
    @Override
    public Optional<Peer> findById(Long id) {
        return peerRepository.findById(id);
    }
}
