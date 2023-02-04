package com.imaginecup.entz.service.implement;

import com.imaginecup.entz.domain.Member;
import com.imaginecup.entz.repository.MemberRepository;
import com.imaginecup.entz.service.MemberService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MemberServiceImp implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImp(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // id로 유저조회
    @Override
    public Member findByUser(String id) {
        return memberRepository.findByUser(id);
    }

    // 존재여부 확인
    @Override
    public  boolean existsByUser(String id) {
        return memberRepository.existsByUser(id);
    }


    // 등록
    @Override
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
    @Override
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
    @Override
    @Transactional
    public int delete(String id) {
        Optional<Member> oMember = memberRepository.findById(id);
        if(((Optional<?>) oMember).isPresent()) {
            memberRepository.delete(oMember.get());
            return 1;
        }
        return 0;
    }
}
