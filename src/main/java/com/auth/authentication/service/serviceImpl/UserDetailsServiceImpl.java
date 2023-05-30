package com.auth.authentication.service.serviceImpl;

import com.auth.authentication.entity.Account;
import com.auth.authentication.repo.AccountRepo;
import com.auth.authentication.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepo accountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepo.findAccountByUserName(username);

        // Account not found
        if(account == null){
            throw new UsernameNotFoundException("User : " + username + " not found");
        }

        // account found but no roles associated
        if(account.getRoles() == null){
            throw new RuntimeException("User : " + username + " has no roles");
        }

        Collection<GrantedAuthority> authorities = account.getRoles()
                                                        .stream()
                                                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                                                        .collect(Collectors.toList());

        // User has different implementation
        // isExpired, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked is set to true
        User user = new User(account.getUserName(), account.getPassword(), authorities);

        return user;
    }
}
