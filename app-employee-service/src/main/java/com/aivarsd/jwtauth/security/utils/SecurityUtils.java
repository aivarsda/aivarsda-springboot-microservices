package com.aivarsd.jwtauth.security.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author Aivars Dalderis
 * @since  04.08.2020
 *
 */

@Component
public class SecurityUtils {
  public String getUsername(){
    return SecurityContextHolder.getContext().getAuthentication().getName();
  }
}
