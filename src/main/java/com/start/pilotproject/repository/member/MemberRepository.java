package com.start.pilotproject.repository.member;

import java.util.Optional;


import com.start.pilotproject.domain.member.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long>{

    @Transactional(readOnly = true)
    Optional<Member> findByEmail(String email);
    
    @Transactional
    @Modifying
    @Query("UPDATE Member m " +
            "SET m.enabled = TRUE WHERE m.email = ?1")
    int enableMember(String email);
}
