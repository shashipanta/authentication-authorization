package com.auth.authentication.controller;

import com.auth.authentication.dto.CustomMessage;
import com.auth.authentication.dto.request.AccountRequest;
import com.auth.authentication.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public CustomMessage attemptLogin(@RequestBody AccountRequest accountRequest){
        return authService.login(accountRequest);
    }

    @GetMapping("/logout")
    public CustomMessage attemptLogout(String userName){
        return authService.logOut(userName);
    }
}
