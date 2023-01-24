package com.imaginecup.entz.service;

import com.imaginecup.entz.domain.Member;
import com.imaginecup.entz.domain.Peer;
import com.imaginecup.entz.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
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
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }


    // 등록
    @Transactional
    public Member save(Member value) {
        Member user = Member.builder()
                .id(value.getId())
                .name(value.getName())
                .gender(value.getGender())
                .level(value.getLevel()).build();
        return memberRepository.save(user);
    }

    // 삭제
    @Transactional
    public int delete(long id) {
        Optional<Member> oMember = memberRepository.findById(id);
        if(oMember.isPresent()) {
            memberRepository.delete(oMember.get());
            return 1;
        }
        return 0;
    }
}



