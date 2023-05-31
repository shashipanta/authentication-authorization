package com.auth.authentication.dto.request;

import com.auth.authentication.entity.Account;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AccountRequest {

    private String name;
    private String password;

    public static Account toAccount(AccountRequest accountRequest){
        Account account = new Account();
        account.setUserName(accountRequest.getName());
        account.setPassword(accountRequest.getPassword());
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        return account;
    }
}
