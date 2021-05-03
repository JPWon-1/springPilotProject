package com.start.pilotproject.repository.account;

import java.util.Optional;

import com.start.pilotproject.domain.account.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>{
    Optional<Account> findByEmail(String email);
}
