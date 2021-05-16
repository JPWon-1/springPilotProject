package com.start.pilotproject.controller.member;

import java.util.Optional;

import com.start.pilotproject.controller.member.dto.MemberDto;
import com.start.pilotproject.domain.member.Member;
import com.start.pilotproject.repository.member.MemberRepository;
import com.start.pilotproject.service.member.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @PostMapping("/signUp") // 회원가입
    public String signUp(@RequestBody MemberDto memberDto) {
        System.out.println("hihihii");
        memberService.signUp(memberDto.toEntity());
        return "/join";
    }


}
