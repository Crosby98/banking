package com.chisw.banking.web.rest;

import com.chisw.banking.repository.UserRepository;
import com.chisw.banking.service.UserService;
import com.chisw.banking.service.dto.UserDTO;
import com.chisw.banking.web.rest.errors.EmailAlreadyUsedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    @PostMapping("/users/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO userDTO) throws Exception {
        if (userDTO.getId() != null) {
            throw new Exception("A new user cannot already have an ID");
        } else if (userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException(userDTO.getEmail());
        } else {
            userService.registerUser(userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/users/successful")
    public ResponseEntity<String> successfulAuthentication() {
        return new ResponseEntity<>("Successfully authenticated", HttpStatus.OK);
    }
}
