package com.start.pilotproject.security.auth;

import java.util.Optional;


import com.start.pilotproject.domain.member.Member;
import com.start.pilotproject.repository.member.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


//security설정에서 ("/login");
//login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어있는 loadByUsername 함수가 실행된다.

@Service
public class PrincipalDetailsService implements UserDetailsService{
    private static final String USER_NOT_FOUND_MSG = "%s로 가입된 회원을 찾을 수 없습니다";

    @Autowired
    private MemberRepository memberRepository;

    //시큐리티 session(Authentication(내부 UserDetails))
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> memberEntity = memberRepository.findByEmail(email);
        if(memberEntity.isPresent()){
            return new PrincipalDetails(memberEntity.get());
        }else{
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email));
        }
    }
    
    
}
