package com.imaginecup.entz.service;

import com.imaginecup.entz.domain.Peer;
import java.util.Optional;

public interface PeerService {
     Optional<Peer> findById(Long id);
}
