package com.aivarsd.jwtauth.controller;

import javax.validation.Valid;

import com.aivarsd.jwtauth.dtos.request.LoginRequest;
import com.aivarsd.jwtauth.dtos.request.SignUpRequest;
import com.aivarsd.jwtauth.dtos.response.ApiErrorResponse;
import com.aivarsd.jwtauth.dtos.response.JwtResponse;
import com.aivarsd.jwtauth.repository.RoleRepository;
import com.aivarsd.jwtauth.security.services.AuthService;
import com.aivarsd.jwtauth.security.utils.RandomUtil;
import com.aivarsd.jwtauth.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aivarsd.jwtauth.entities.User;
import com.aivarsd.jwtauth.repository.UserRepository;

/**
 * @author Aivars Dalderis
 * @since  04.08.2020
 *
 */

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;


    @PostMapping("/signin")
    public ResponseEntity<? extends Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("/login -> loginRequest : \n  " + loginRequest);

        Authentication authentication = authService.authenticate(loginRequest.getUsername(),loginRequest.getPassword());
        String jwt = authService.generateJwtToken(authentication);
        User user = userService.getUserByUserName(loginRequest.getUsername());
        user.setPassword("");

        return ResponseEntity.ok(new JwtResponse(jwt, user, "Log-In Successful, welcome " + loginRequest.getUsername() + " !"));
    }

    @ApiOperation(value = "Update employee", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resource updated"),
            @ApiResponse(code = 400, message = "Request is invalid"),
            @ApiResponse(code = 404, message = "Resource you try to update is not found"),
            @ApiResponse(code = 500, message = "Internal unexpected error")
    })
    @PostMapping("/signup")
    public ResponseEntity<? extends Object> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        log.info("/signup -> signUpRequest : \n  " + signUpRequest);
//        System.out.println("generateJwtToken for the aivarsd.app.jwtSecret:"+ RandomUtil.genRandom256CharsAsBase64());

        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return getResponseEntityError(HttpStatus.BAD_REQUEST,"Username is already taken!");
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return getResponseEntityError(HttpStatus.BAD_REQUEST,"Email is already in use!");
        }

        User user = userService.createUserAccount(
                signUpRequest.getName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getPassword(),
                signUpRequest.getRole()
        );

        Authentication authentication = authService.authenticate(signUpRequest.getUsername(),signUpRequest.getPassword());
        String jwt = authService.generateJwtToken(authentication);

        user.setPassword("");
        log.debug("/signup -> Success : User registered successfully! -> user:\n  " + user);
        return ResponseEntity.ok(new JwtResponse(jwt, user, "Sign-Up Successful, welcome " + signUpRequest.getUsername() + " !"));
    }

    @Override
    String getApiType() {
        return ApiErrorResponse.ApiType.AUTH.getValue();
    }
}