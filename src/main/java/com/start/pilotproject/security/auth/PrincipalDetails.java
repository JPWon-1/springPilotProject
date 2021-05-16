package com.start.pilotproject.security.auth;

import java.util.ArrayList;
import java.util.Collection;

import com.start.pilotproject.domain.member.Member;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//시큐리티가 /login 주소요청이 오면 낚아채서 로그인을 진행시킨다.
//로그인이 진행이 완료가 되면 시큐리티 session을 만들어준다.(Security ContextHolder)
//오브젝트 타입=> Authentication타입 객체
//Authentication 안에 User정보가 있어야 됨.
//User오브젝트 타입 => UserDetails 타입 객체

//Security Session => Authentication 객체이여야 한다. => UserDetails(PrincipalDetails)타입

public class PrincipalDetails implements UserDetails{

    private Member member; //콤포지션

    public PrincipalDetails(Member member){
        this.member = member;
    }

    //해당 유저의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority(){
            @Override
            public String getAuthority() {
                return member.getRole().getKey();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        //예를 들어 사이트에서 로그인을 1년동안 안하면 휴먼계정으로 변환
        //현재시간 - 로그인 시간 => 1년을 초과하면 return false
        return true;
    }
    
}
