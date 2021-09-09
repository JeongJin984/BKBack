package com.example.bkback.security.service;

import com.example.bkback.db.dto.AuthenticationDto;
import com.example.bkback.db.entity.Account;
import com.example.bkback.db.repository.account.AccountRepository;
import com.example.bkback.security.util.AccountContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthenticationDto account = accountRepository.findAuthInfoByUsername(username);

        if(account == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        Set<GrantedAuthority> userRoles = new HashSet<>();

        return new AccountContext(account, userRoles);
    }
}