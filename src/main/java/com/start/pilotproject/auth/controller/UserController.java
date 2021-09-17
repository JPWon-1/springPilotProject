package com.start.pilotproject.auth.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.start.pilotproject.controller.member.dto.MemberDto;
import com.start.pilotproject.service.member.MemberService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class UserController {
    private final MemberService memberService;

    public UserController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @PostMapping("/test-redirect")
    public void testRedirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/api/user");
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody MemberDto memberDto) {
        return ResponseEntity.ok(memberService.signUp(memberDto.toEntity()));
    }

}