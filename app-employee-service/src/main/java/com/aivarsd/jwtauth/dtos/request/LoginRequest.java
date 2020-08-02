package com.aivarsd.jwtauth.dtos.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * @author Aivars Dalderis
 * @since  04.08.2020
 *
 */

@Data
public class LoginRequest {
    @NotBlank
    @Size(min=3, max = 60)
    private String username;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}