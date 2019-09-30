package com.chisw.service;

import com.chisw.AbstractTest;
import com.chisw.banking.domain.User;
import com.chisw.banking.repository.UserRepository;
import com.chisw.banking.service.UserService;
import com.chisw.banking.service.dto.UserDTO;
import com.chisw.banking.service.mapper.UserMapper;
import com.chisw.banking.web.rest.errors.EmailAlreadyUsedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest extends AbstractTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void shouldSignUp() {
        UserDTO userDto = createUserDto();

        when(userRepository.findOneByEmailIgnoreCase(any(String.class))).thenReturn(Optional.empty());

        when(passwordEncoder.encode(any(String.class))).thenReturn("{bcrypt}$2a$10$wtZcFs8YAwHEbqdS1YcwpOelwkvqPw5hHTNLZ/S2KymEfdmezN7wS");

        userService.registerUser(userDto);

        verify(userRepository, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode(any(String.class));
        verify(userRepository, times(1)).findOneByEmailIgnoreCase(any(String.class));
    }

    @Test(expected = EmailAlreadyUsedException.class)
    public void shouldThrowEmailAlreadyInUseException() {
        UserDTO userDto = createUserDto();

        when(userRepository.findOneByEmailIgnoreCase(any(String.class))).thenReturn(Optional.of(new User()));

        userService.registerUser(userDto);

        verify(userRepository, times(0)).save(any(User.class));
        verify(passwordEncoder, times(0)).encode(any(String.class));
        verify(userRepository, times(1)).findOneByEmailIgnoreCase(any(String.class));
    }

    @Test
    public void shouldReturnAccountInfo() {
        String email = "chisw@mail.ru";

        User user = createUser();

        when(userRepository.findOneByEmailIgnoreCase(email)).thenReturn(Optional.of(user));

        userService.getUserAccountBalanceByEmail(email);

        verify(userRepository, times(1)).findOneByEmailIgnoreCase(any(String.class));
    }
}
