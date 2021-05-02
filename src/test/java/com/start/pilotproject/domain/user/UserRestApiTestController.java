package com.start.pilotproject.domain.user;

import com.start.pilotproject.repository.user.UserRepository;
import com.start.pilotproject.service.user.UserService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRestApiTestController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("회원 가입")
    public void join(){
        //given
        String email = "testAddress";
        String password = "testPassword";
        //when
        User user = new User(email,password);
        //then
        User user2 = userRepository.save(user);
        System.out.println(user2);

    }
}
