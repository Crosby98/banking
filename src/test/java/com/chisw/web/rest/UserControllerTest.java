package com.chisw.web.rest;

import com.chisw.AbstractTest;
import com.chisw.banking.repository.UserRepository;
import com.chisw.banking.service.UserService;
import com.chisw.banking.service.dto.UserDTO;
import com.chisw.banking.web.rest.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void signupHappy() throws Exception {
        UserDTO userDto = createUserDto();
        String contentJson = createJsonFromObject(userDto);

        mockMvc.perform(post("/api/users/register")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(contentJson))
            .andExpect(status().isOk());
    }
}
