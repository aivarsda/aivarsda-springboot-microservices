package com.aivarsd.jwtauth.controller;

import com.aivarsd.jwtauth.dtos.response.ApiErrorResponse;
import com.aivarsd.jwtauth.security.services.UserPrinciple;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aivars Dalderis
 * @since  04.08.2020
 *
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TestApiController extends BaseController {
	@GetMapping("/api/test/public")
	public String publicAccess(@AuthenticationPrincipal UserPrinciple user) {
		return ">>> " + user.toString() + " -> got Access to : Public content";
	}

	@GetMapping("/api/test/user")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public String userAccess(@AuthenticationPrincipal UserPrinciple user) {
		return ">>> " + user.toString() + " -> got Access to : User content";
	}

	@GetMapping("/api/test/pm")
	@PreAuthorize("hasRole('ROLE_PM') or hasRole('ROLE_ADMIN')")
	public String projectManagementAccess(@AuthenticationPrincipal UserPrinciple user) {
		return ">>> " + user.toString() + " -> got Access to : Project Management content";
	}
	
	@GetMapping("/api/test/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String adminAccess(@AuthenticationPrincipal UserPrinciple user) {
		return ">>> " + user.toString() + " -> got Access to : Admin content";
	}

	@Override
	String getApiType() {
		return ApiErrorResponse.ApiType.API.getValue();
	}
}