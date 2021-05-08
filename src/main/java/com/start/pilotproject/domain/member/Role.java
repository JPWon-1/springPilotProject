package com.start.pilotproject.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN ("ROLE_ADMIN","어드민 계정"),
    USER  ("ROLE_USER" , "일반 유저 ");

    private final String key;
    private final String title;
    
}
