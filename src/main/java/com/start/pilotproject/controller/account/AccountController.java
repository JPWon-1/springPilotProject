package com.start.pilotproject.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
}
