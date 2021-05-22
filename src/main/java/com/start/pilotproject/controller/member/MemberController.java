package com.start.pilotproject.controller.member;

import java.util.HashMap;
import java.util.Map;

import com.start.pilotproject.config.auth.PrincipalDetails;
import com.start.pilotproject.controller.member.dto.MemberDto;
import com.start.pilotproject.repository.member.MemberRepository;
import com.start.pilotproject.service.member.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    //oauth로 로그인 하던, 일반 로그인을 하던 principalDetails로 받는것을 확인하기 위한 테스트 컨트롤러
    @GetMapping("/user")
    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("principalDetails:"+principalDetails.getUser());
        return "user 정보 확인중..";
        //"$2a$10$q1CMMHnG7yrg5ZUhpwm09ulZhT5BR/FFm5Hy5Z7vG3vPlKOPEua52"
        //"$2a$10$YgH/DP..ZNG4DIBD7uh6g.u0DiMUAnIA1UzkKqNWgH2UhMdNVFB4W"
    }

    @GetMapping("/test/login")
    //DI(의존성 주입)
    public @ResponseBody String loginTest(Authentication authentication, 
        @AuthenticationPrincipal PrincipalDetails userDetails){
        System.out.println("/test/login============");
        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();//다운 캐스팅 
        System.out.println("authentication:"+principalDetails);
        System.out.println("userEmail:"+userDetails);
        return "세션 정보 확인하기";
    }

    @GetMapping("/test/oauth/login")
    //DI(의존성 주입)
    public @ResponseBody String testOauthLogin(Authentication authentication,
        @AuthenticationPrincipal OAuth2User oauth){
        System.out.println("/test/login============");
        OAuth2User oauth2User = (OAuth2User)authentication.getPrincipal();//다운캐스팅 해야함

        System.out.println(oauth2User);
        System.out.println("oauth2User:"+oauth.getAttributes());
        return "OAuth2 세션 정보 확인하기";
    }

    //스프링 시큐리티는 자기만의 시큐리티 세션을 가지고 있음
    //세션안에 시큐리티 세션이 따로 있음
    //시큐리티 세션 안에 들어갈 수 있는 타입은 Authentication 객체만 들어갈 수 있음
    //Authentication 안에 들어 갈 수 있는 타입은
    //1. UserDetails <- 일반적인 로그인
    //2. Oauth2User  <- oAuth 로그인
    //Oauth2 객체도 PrincipalDetails 로 잡아서 Authentication 객체 안에 넣어버리면
    //oauth로 로그인하던 일반적인 로그인을 하던 PrincipalDetails 타입으로 받을 수 있다!
    //public class PrincipalDetails implements UserDetails, OAuth2User <- UserDetails, Oauth2User를 impl하여
    //다형성을 이용하여 두 객체를 묶는다!


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/join")
    public String joinPage() {
        return "join";
    }

    @ResponseBody
    @PostMapping("/signUp") // 회원가입
    public Map<String, Object> signUp(@RequestBody MemberDto memberDto) {
        Map<String, Object> response = new HashMap<>();
        response.put("response", memberService.signUp(memberDto.toEntity()));
        return response;
    }

    @Secured("ROLE_ADMMIN")
    @GetMapping("/info")
    public @ResponseBody String info(){
        return "개인정보";
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole(ROLE_ADMIN)")
    @GetMapping("/data")
    public @ResponseBody String data(){
        return "중요한 데이터";
    }

}
