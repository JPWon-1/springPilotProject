package com.start.pilotproject.controller.member.dto;

import com.start.pilotproject.domain.member.Member;

import lombok.Getter;

@Getter
public class MemberDto {
    private String email;
    private String password;

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .password(password)
                .build();
    }

    public void bcryptionPassword(String password){
        this.password = password;
    }
}
