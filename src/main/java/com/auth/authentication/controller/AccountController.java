package com.auth.authentication.controller;

import com.auth.authentication.dto.request.AccountRequest;
import com.auth.authentication.dto.response.AccountResponse;
import com.auth.authentication.entity.Account;
import com.auth.authentication.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping(value = "/")
    public AccountResponse createAccount(@RequestBody AccountRequest accountRequest){
        return accountService.createAccount(accountRequest);
    }

    @GetMapping(value = "/")
    public List<AccountResponse> getRegisteredAccounts(){
        return accountService.viewRegisteredAccounts();
    }
}
