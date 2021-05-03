package com.start.pilotproject.domain.user;

import com.start.pilotproject.domain.account.Account;
import com.start.pilotproject.repository.account.AccountRepository;
import com.start.pilotproject.service.account.AccountService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AccountRestApiTest {
    @Autowired
    AccountService userService;

    @Autowired
    AccountRepository userRepository;

    @Test
    @DisplayName("회원 가입")
    public void join(){
        //given
        String email = "testAddress";
        String password = "testPassword";
        //when
        Account user = new Account.Builder()
            .email(email)
            .password(password)
            .build();
        //then
        Account user2 = userRepository.save(user);
        System.out.println(user2);

    }
}
