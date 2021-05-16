package com.start.pilotproject.service.member;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import com.start.pilotproject.domain.member.Member;
import com.start.pilotproject.domain.registration.token.ConfirmationToken;
import com.start.pilotproject.repository.member.MemberRepository;
import com.start.pilotproject.service.registration.token.ConfirmationTokenService;

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
    public Long signUp(Member member) {
        boolean userExists = memberRepository.findByEmail(member.getEmail()).isPresent();
        if(userExists) {
            throw new IllegalStateException("이메일이 이미 존재합니다.");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(member.getPassword());
        member.bcryptionPassword(encodedPassword);
        memberRepository.save(member);
        return member.getId();
    }
}
