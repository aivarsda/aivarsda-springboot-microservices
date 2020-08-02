package com.aivarsd.jwtauth.security.services;

import com.aivarsd.jwtauth.entities.User;
import com.aivarsd.jwtauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Aivars Dalderis
 * @since  04.08.2020
 *
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                	.orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found with -> username : " + username)
        );

        return UserPrinciple.build(user);
    }

    @Transactional
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found with -> email : " + email)
                );

        return UserPrinciple.build(user);
    }
}