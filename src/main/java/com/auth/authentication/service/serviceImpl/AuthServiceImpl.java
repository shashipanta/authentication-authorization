package com.auth.authentication.service.serviceImpl;

import com.auth.authentication.dto.CustomMessage;
import com.auth.authentication.dto.request.AccountRequest;
import com.auth.authentication.entity.Account;
import com.auth.authentication.repo.AccountRepo;
import com.auth.authentication.service.AuthService;
import com.auth.authentication.utils.JsonWebToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepo accountRepo;

    @Value("${jwt.secret-key}")
    private String secretKey;

    private String authorizeAccount(AccountRequest accountRequest){
        Account savedAccount = accountRepo.findAccountByUserName(accountRequest.getName());

        Boolean isCredentialsValid = passwordEncoder.matches(accountRequest.getPassword(), savedAccount.getPassword());

        if(isCredentialsValid) {
            // generate token
            return JsonWebToken.generateAccessToken(savedAccount, secretKey );
        }

        return null;

    }

    @Override
    public CustomMessage login(AccountRequest accountRequest) {

        String token = authorizeAccount(accountRequest);

        if(token != null){
            return new CustomMessage("SUCCESS_CODE", "Login successfully", token );
        }

        return new CustomMessage("FAILED_CODE", "Login failed", "No-token");
    }

    @Override
    public CustomMessage register(AccountRequest accountRequest) {
        return null;
    }

    @Override
    public CustomMessage logOut(String userName) {
        Account foundAccount = accountRepo.findAccountByUserName(userName);

        return null;
    }
}
