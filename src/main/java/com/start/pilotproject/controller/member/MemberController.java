package com.start.pilotproject.controller.member;

import java.util.HashMap;
import java.util.Map;

import com.start.pilotproject.controller.member.dto.MemberDto;
import com.start.pilotproject.repository.member.MemberRepository;
import com.start.pilotproject.service.member.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
