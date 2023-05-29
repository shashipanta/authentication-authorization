package com.auth.authentication.repo;

import com.auth.authentication.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {

    Account findAccountByUserName(String userName);
}
