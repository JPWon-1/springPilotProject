package com.start.pilotproject.repository.member;



import com.start.pilotproject.domain.member.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long>{

    @Transactional(readOnly = true)
    Member findByEmail(String email);//Jpa Query Methods
    
}
