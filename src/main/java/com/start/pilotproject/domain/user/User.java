package com.start.pilotproject.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
// @Entity
public class User {
    
    private Long id;
    private String name;
    private String picture;
    private Role role;

}
