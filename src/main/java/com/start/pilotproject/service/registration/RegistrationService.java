package com.start.pilotproject.service.registration;

import com.start.pilotproject.controller.registration.EmailValidator;
import com.start.pilotproject.controller.registration.RegistrationRequest;
import com.start.pilotproject.domain.member.Member;
import com.start.pilotproject.domain.member.Role;
import com.start.pilotproject.service.member.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private MemberService memberService;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("유효하지 않은 이메일입니다");
        }
        return memberService.signUpUser(
            new Member(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                Role.USER
            )    
        );
    }
    
}
