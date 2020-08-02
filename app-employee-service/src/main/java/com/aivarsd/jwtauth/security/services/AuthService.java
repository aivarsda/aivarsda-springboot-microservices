package com.aivarsd.jwtauth.security.services;

import com.aivarsd.jwtauth.security.jwt.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


/**
 * @author Aivars Dalderis
 * @since  04.08.2020
 *
 */

@Slf4j
@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;



    public Authentication authenticate(String userName, String password){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userName,
                        password
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    public String generateJwtToken(Authentication authentication){
       return jwtProvider.generateJwtToken(authentication);
    }
}