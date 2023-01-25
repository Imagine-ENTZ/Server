package com.imaginecup.entz.service;

import com.imaginecup.entz.domain.Member;
import com.imaginecup.entz.domain.Peer;
import com.imaginecup.entz.repository.MemberRepository;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // id로 조회
    public Member findByUser(String id) {
        return memberRepository.findByUser(id);
    }

    // 존재여부 확인
    public  boolean existsByUser(String id) {
        return memberRepository.existsByUser(id);
    }

    // 등록
    @Transactional
    public Member save(Member value) {

        if(memberRepository.existsByUser(value.getUser())){
            return null;
        }
        else {
            Member user = Member.builder()
                    .name(value.getName())
                    .user(value.getUser())
                    .password(value.getPassword()).build();
            return memberRepository.save(user);
        }
    }

    // 로그인
    @Transactional
    public boolean login(Member value) {
        if(memberRepository.existsByUser(value.getUser())){
            Member oMember = memberRepository.findByUser(value.getUser());

            if(oMember.getPassword().equals(value.getPassword())) {
                return true;
            }
            else {
                return false;
            }
        }
        else
            return false;
    }

    // 삭제
    @Transactional
    public int delete(String id) {
        Optional<Member> oMember = memberRepository.findById(id);
        if(oMember.isPresent()) {
            memberRepository.delete(oMember.get());
            return 1;
        }
        return 0;
    }
}



