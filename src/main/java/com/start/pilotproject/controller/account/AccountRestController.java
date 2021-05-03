package com.start.pilotproject.controller.account;

import com.start.pilotproject.domain.account.Account;
import com.start.pilotproject.repository.account.AccountRepository;
import com.start.pilotproject.service.account.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountRestController {

    @Autowired
    AccountService accountService;

    @GetMapping(value = "/create")
    public Account save() {
        Account account = new Account.Builder()
            .email("user@gmail.com")
            .password("1234")
            .build();
        return accountService.save(account);
    }
}
