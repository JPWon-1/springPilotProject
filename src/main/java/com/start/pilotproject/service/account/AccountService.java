package com.start.pilotproject.service.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.start.pilotproject.domain.account.Account;
import com.start.pilotproject.repository.account.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService{

    @Autowired
    private AccountRepository acountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // join
    @Transactional
    public Account save(Account account) {
        return acountRepository.save(account);
    }
    // login
    // findByEmail
    // findAllConnectedUser
    // insert
    // update

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> account = acountRepository.findByEmail(email);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(account.get().getEmail(),account.get().getPassword(),authorities);
    }
  
}
