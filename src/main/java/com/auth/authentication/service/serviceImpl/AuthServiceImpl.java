package com.auth.authentication.service.serviceImpl;

import com.auth.authentication.config.CustomPasswordEncoder;
import com.auth.authentication.dto.CustomMessage;
import com.auth.authentication.dto.request.AccountRequest;
import com.auth.authentication.entity.Account;
import com.auth.authentication.repo.AccountRepo;
import com.auth.authentication.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final CustomPasswordEncoder customPasswordEncoder;
    private final AccountRepo accountRepo;

    @Override
    public CustomMessage login(AccountRequest accountRequest) {
        Account savedAccount = accountRepo.findAccountByUserName(accountRequest.getName());

        Boolean isCredentialsValid = customPasswordEncoder.isMatching(savedAccount.getPassword(), accountRequest.getPassword());

        if(isCredentialsValid){
            return new CustomMessage("SUCCESS_CODE", "Login successfully");
        }

        return new CustomMessage("FAILED_CODE", "Login failed");
    }

    @Override
    public CustomMessage register(AccountRequest accountRequest) {
        return null;
    }

    @Override
    public CustomMessage logOut() {
        return null;
    }
}
