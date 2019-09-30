package com.chisw.banking.security;

import com.chisw.banking.repository.UserRepository;
import com.chisw.banking.web.rest.errors.InvalidEmailException;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Component("userDetailsService")
@RequiredArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String email) {
        log.debug("Authenticating {}", email);

        if (!new EmailValidator().isValid(email, null)) {
            throw new InvalidEmailException(email);
        }

        return userRepository.findOneByEmailIgnoreCase(email)
            .map(user -> new User(email, user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(AuthoritiesConstants.USER))))
            .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " was not found in the database"));
    }
}
