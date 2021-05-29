package com.start.pilotproject.service.member;


import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import com.start.pilotproject.domain.member.Member;
import com.start.pilotproject.repository.member.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Long signUp(Member newMember) {
        Optional<Member> oldMember = memberRepository.findByEmail(newMember.getEmail());
        if(oldMember.isPresent()){
            throw new IllegalStateException("이메일이 이미 존재합니다.");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(newMember.getPassword());
        newMember.bcryptionPasswordAndGiveRole(encodedPassword);
        memberRepository.save(newMember);
        return newMember.getId();
    }
}
