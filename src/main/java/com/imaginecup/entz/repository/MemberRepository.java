package com.imaginecup.entz.repository;

import com.imaginecup.entz.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {



    Member findByUser(String id);

    boolean existsByUser(String id);
}
