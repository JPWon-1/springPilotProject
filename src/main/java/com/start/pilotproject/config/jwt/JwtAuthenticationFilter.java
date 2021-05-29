package com.start.pilotproject.config.jwt;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.start.pilotproject.config.auth.PrincipalDetails;
import com.start.pilotproject.domain.member.Member;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

//스프링 시큐리티에서 UsernamePasswordAuthenticationFilter가 있음.
// /login 요청해서 username, password를 전송하면 (post)
// UsernamePasswordAuthenticationFilter 가 동작 함.

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    // login 요청을 하면 로그인 시도를 위해서 실행되는 함수.
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // 1. 아이디와 패스워드를 받아서
        // 2. 정상인지 로그인 시도를 한다.
        // ㄴauthenticationManager로 로그인 시도를 하면 PrincipalDetailsService가 호출된다.
        // ㄴloadUserByUsername 이 자동으로 실행 된다.
        // 3. PrincipalDetails를 세션에 담고
        // ㄴ세션에 담는 이유는 세션에 담아야지만 권한 관리를 할 수 있다.
        // 4. JWT를 만들어서 응답해주면 됨.

        try {
            System.out.println(request.getInputStream().toString());
            ObjectMapper om = new ObjectMapper();
            Member member = om.readValue(request.getInputStream(), Member.class);
            System.out.println(member.getEmail()+"/"+member.getPassword());

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    member.getEmail(), member.getPassword());
                    
            // 2. PrincipalDetailsService의 loadByUsername() 함수가 실행됨.
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            // authentication 객체가 session영역에 저장됨 => 로그인이 되었다는 뜻.
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println(principalDetails.getUser().getEmail());
            
            // authentication 객체가 session영역에 저장을 해야하고 그 방법이 return해주면 됨.
            // 리턴의 이유는 권한 관리를 security가 대신 해주기 때문에 편하려고 하는거임
            // 굳이 JWT 토큰을 사용하면서 세션을 만들 이유가 없음. 단지 권한처리떄문에 넣음

            return authentication;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //attemptAuthentication에서 인증이 정상적으로 되었으면 succesfulAuthentication이 실행됨
    // JWT토큰을 만들어서 request요청한 사용자에게로 response
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        System.out.println("successfulAuthentication이 실행됨");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        // Hash암호 방식
        String jwtToken = JWT.create()//JWT라이브러리
                .withSubject(JwtProperties.HEADER_STRING)
                .withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
                .withClaim("id", principalDetails.getUser().getEmail())
                .withClaim("username", principalDetails.getUser().getFirstName())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtToken);

    }
}
