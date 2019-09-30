package com.chisw.banking.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static Optional<String> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
            .map(authentication -> {
                if (authentication.getPrincipal() instanceof UserDetails) {
                    UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                    return springSecurityUser.getUsername();
                }
                return null;
            });
    }

    public static boolean isAuthenticated() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
            .map(authentication -> {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.addAll(authentication.getAuthorities());
                return authorities.stream()
                    .noneMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(AuthoritiesConstants.ANONYMOUS));
            })
            .orElse(false);
    }
}
