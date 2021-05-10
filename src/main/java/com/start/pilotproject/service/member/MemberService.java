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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements UserDetailsService {
    private static final String USER_NOT_FOUND_MSG = "%s로 가입된 회원을 찾을 수 없습니다";
    
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    // join
    @Transactional
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(Member member) {
        boolean userExists = memberRepository.findByEmail(member.getEmail()).isPresent();
        if(userExists) {
            throw new IllegalStateException("이메일이 이미 존재합니다.");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);
        memberRepository.save(member);
        // TODO: Send confirmation token
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
            token,
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(15),
            member
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        //TODO : send email

        return token;
    }

    public int enableMember(String email) { 
        return memberRepository.enableMember(email);
    }
}
