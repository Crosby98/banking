package com.chisw.banking.service;

import com.chisw.banking.domain.Account;
import com.chisw.banking.domain.Status;
import com.chisw.banking.domain.User;
import com.chisw.banking.repository.UserRepository;
import com.chisw.banking.service.dto.UserDTO;
import com.chisw.banking.web.rest.errors.EmailAlreadyUsedException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void registerUser(UserDTO userDTO) throws EmailAlreadyUsedException {
        Optional<User> oneByEmailIgnoreCase = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
        if (oneByEmailIgnoreCase.isPresent()) {
            throw new EmailAlreadyUsedException(userDTO.getEmail());
        }
        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setSurName(userDTO.getSurName());
        newUser.setPhone(userDTO.getPhone());
        newUser.setEmail(userDTO.getEmail().toLowerCase());
        newUser.setStatus(Status.ACTIVE.getValue());
        newUser.setAccount(Account.createNewActiveAccount());
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
    }
}
