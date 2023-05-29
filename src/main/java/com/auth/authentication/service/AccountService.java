package com.auth.authentication.service;

import com.auth.authentication.dto.request.AccountRequest;
import com.auth.authentication.dto.response.AccountResponse;

import java.util.List;

public interface AccountService {

    AccountResponse createAccount(AccountRequest accountRequest);

    List<AccountResponse> viewRegisteredAccounts();

    AccountResponse updateAccount(Long accountId, AccountRequest accountRequest);

    void deleteAccount(Long accountId);
}
