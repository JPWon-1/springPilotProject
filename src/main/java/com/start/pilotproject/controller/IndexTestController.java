package com.start.pilotproject.controller;

import com.start.pilotproject.config.auth.PrincipalDetails;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexTestController {

    @PostMapping("/token")
    public String token(){
        return "<h1>token</h1>";
    }

    @GetMapping("/api/v1/user")
    public String member(Authentication authentication){
        PrincipalDetails principal = (PrincipalDetails)authentication.getPrincipal();
        System.out.println("authentication:"+ principal.getUser().getEmail());
        return "member";
    }
   
}
