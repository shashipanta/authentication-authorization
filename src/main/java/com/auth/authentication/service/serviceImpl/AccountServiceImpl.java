package com.auth.authentication.service.serviceImpl;

import com.auth.authentication.config.CustomPasswordEncoder;
import com.auth.authentication.dto.request.AccountRequest;
import com.auth.authentication.dto.response.AccountResponse;
import com.auth.authentication.entity.Account;
import com.auth.authentication.entity.Role;
import com.auth.authentication.repo.AccountRepo;
import com.auth.authentication.repo.RoleRepo;
import com.auth.authentication.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;
    private final RoleRepo roleRepo;

    private final CustomPasswordEncoder customPasswordEncoder;

    @Override
    public AccountResponse createAccount(AccountRequest accountRequest)  {
        Account account = AccountRequest.toAccount(accountRequest);

        // encrypt password
        account.setPassword(customPasswordEncoder.encrypt(account.getPassword()));

        // add default role to any user
        Role roleUser = roleRepo.findRoleByName("ROLE_USER");
        Set<Role> roleSet = Collections.singleton(roleUser);

        account.setRoles(roleSet);

        account = accountRepo.save(account);
        return AccountResponse.toAccountResponse(account);
    }

    @Override
    public List<AccountResponse> viewRegisteredAccounts() {
        List<Account> accountList = accountRepo.findAll();
        return accountList.stream()
                            .map(AccountResponse::toAccountResponse)
                            .collect(Collectors.toList());
    }

    @Override
    public AccountResponse updateAccount(Long accountId, AccountRequest accountRequest) {
        return null;
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountRepo.deleteById(accountId);
    }
}
