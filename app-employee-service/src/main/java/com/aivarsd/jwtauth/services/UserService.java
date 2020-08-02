package com.aivarsd.jwtauth.services;

import com.aivarsd.jwtauth.entities.Role;
import com.aivarsd.jwtauth.entities.User;
import com.aivarsd.jwtauth.repository.RoleRepository;
import com.aivarsd.jwtauth.repository.UserRepository;
import com.takeaway.test.common.messages.Action;
import com.takeaway.test.common.messages.EventMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Aivars Dalderis
 * @since  04.08.2020
 *
 */

@Slf4j
@Service
public class UserService {

//    private final Source rabbitMqSource;
//
//    @Autowired
//    public UserService(Source rabbitMqSource) {
//        this.rabbitMqSource = rabbitMqSource;
//    }

    @Autowired
    Source rabbitMqSource;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Transactional
    public User getUserByEmail(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Could not find user with the following email: " + email)
                );

        return user;
    }

    @Transactional
    public User getUserByUserName(String userName) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(userName)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Could not find user with the username: " + userName)
                );

        return user;
    }

    public User createUserAccount(String name, String username, String email, String password, Set<String> rolesSet) {
        User user = new User(name, username, email, encoder.encode(password));
        log.debug("createUserAccount -> user:\n  " + user);
        Set<Role> roles = new HashSet<>();
        //TODO - Probably better to change the Roles checking into one DB hit, which will bring all the roles. Instead of several DB hits for each role.
        rolesSet.forEach(roleNameStr -> {
            Role role = roleRepository.findByName(roleNameStr)
                    .orElseThrow(() -> new RuntimeException("Fail! -> User Role: "+ roleNameStr +" does not exist."));
            roles.add(role);
        });
        user.setRoles(roles);

        userRepository.save(user);
        sendEventMessage(String.valueOf(user.getId()), Action.CREATE);
        log.debug("createUserAccount -> Success!");

        return user;
    }

    private void sendEventMessage(String uuid, Action action) {
//        EventMessage message = EventMessage.builder()
//                .uuid(uuid)
//                .action(action)
//                .build();
//        boolean success = rabbitMqSource.output().send(MessageBuilder.withPayload(message).build());
//
//        if (!success) {
//            log.warn("Failed to send message for employee '{}' with action type '{}'", uuid, action);
//        } else {
//            log.debug("Sent message for employee '{}' with action type '{}'", uuid, action);
//        }
    }
}