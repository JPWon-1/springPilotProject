package com.start.pilotproject.auth.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

   private String username;

   private String password;

   private String nickname;
}