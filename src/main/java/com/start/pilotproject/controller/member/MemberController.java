package com.start.pilotproject.controller.member;

import java.util.HashMap;
import java.util.Map;

// import com.start.pilotproject.config.auth.PrincipalDetails;
import com.start.pilotproject.controller.member.dto.MemberDto;
import com.start.pilotproject.repository.member.MemberRepository;
import com.start.pilotproject.service.member.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
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

}
