package com.auth.authentication.dto.request;

import com.auth.authentication.entity.Account;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountRequest {

    private String name;
    private String password;

    public static Account toAccount(AccountRequest accountRequest){
        Account account = new Account();
        account.setUserName(accountRequest.getName());
        account.setPassword(accountRequest.getPassword());
        return account;
    }
}
