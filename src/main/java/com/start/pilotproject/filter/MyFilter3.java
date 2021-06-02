package com.start.pilotproject.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter3 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("필터 3");

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        
        // 토큰 : 코스라는 인증이 되게하고 그게 아니면 인증을 못하게 하는 예제
        // id,pawd가 정상적으로 들어와서 로그인이 완료 되면 토큰을 만들어주고
        // 그걸 응답 해준다.
        // 요청을 할때마다 header에 Authorization에 value값으로 토큰을 가지고 오는데
        // 그 때 토큰이 넘어오면 이 토큰이 내가 만든 토큰이 맞는지 검증만 하면 됨.(rsa,hsa256)
        
        if(req.getMethod().equalsIgnoreCase("post")){
            String headerAuth = req.getHeader("Authorization");
            System.out.println("헤더="+headerAuth);
            if(headerAuth.equals("cors")){
                System.out.println("필터3에서 인증 됨");
                chain.doFilter(req , res);
            }else{
                System.out.println("필터3에서 인증 안됨");
            }
        }

        chain.doFilter(req, res);
    }
}
