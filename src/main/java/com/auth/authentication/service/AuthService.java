package com.auth.authentication.service;

import com.auth.authentication.dto.CustomMessage;
import com.auth.authentication.dto.request.AccountRequest;

public interface AuthService {

    CustomMessage login(AccountRequest accountRequest);

    CustomMessage register(AccountRequest accountRequest);

    CustomMessage logOut(String userName);
}
