package com.start.pilotproject.config.auth;

import java.util.Objects;


import com.start.pilotproject.domain.member.Member;
import com.start.pilotproject.repository.member.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


//security설정에서 ("/login");
//login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어있는 loadUserByUsername 함수가 실행된다.

@Service
public class PrincipalDetailsService implements UserDetailsService{
    private static final String USER_NOT_FOUND_MSG = "%s로 가입된 회원을 찾을 수 없습니다";

    @Autowired
    private MemberRepository memberRepository;

    //시큐리티 session(Authentication(내부 UserDetails))
    //함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다!
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //String email  <= email로 받고 싶으면 WebSecurityConfig 에서 바꿔야함. usernameParameter("email")
        Member memberEntity = memberRepository.findByEmail(email);
        if(Objects.isNull(memberEntity)){
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email));
        }else{
            return new PrincipalDetails(memberEntity);
        }
    }
    
    
}
