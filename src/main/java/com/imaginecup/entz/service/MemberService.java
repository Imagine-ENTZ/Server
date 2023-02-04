package com.imaginecup.entz.service;

import com.imaginecup.entz.domain.Member;
import org.springframework.stereotype.Service;
@Service
public interface MemberService {

    // id로 조회
    Member findByUser(String id);
    // 존재여부 확인
    boolean existsByUser(String id);
    // 등록
    Member save(Member value);
    // 로그인
    boolean login(Member value);
    // 삭제
    int delete(String id);
}



