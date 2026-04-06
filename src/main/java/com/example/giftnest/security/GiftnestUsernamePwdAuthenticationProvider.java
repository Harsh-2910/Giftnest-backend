package com.example.giftnest.security;

import com.example.giftnest.entity.Customer;
import com.example.giftnest.entity.Role;
import com.example.giftnest.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class GiftnestUsernamePwdAuthenticationProvider implements AuthenticationProvider {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        log.debug(username);
        String pwd = authentication.getCredentials().toString();
        Customer customer = customerRepository.findByEmail(username).orElseThrow(
                ()-> new UsernameNotFoundException("User details not found for the user: "+username)
        );
        log.debug(customer.getEmail(),customer.getMobileNumber());
        Set<Role> roles = customer.getRoles();
        List<SimpleGrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList();
        if(passwordEncoder.matches(pwd,customer.getPasswordHash())){
            log.debug("password matched!!");
            return new UsernamePasswordAuthenticationToken(customer,null, authorities);
        }
        else{
            throw new BadCredentialsException("Invalid Password!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication){
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
