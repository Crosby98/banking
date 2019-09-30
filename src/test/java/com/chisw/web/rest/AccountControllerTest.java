package com.chisw.web.rest;

import com.chisw.banking.domain.Account;
import com.chisw.banking.service.AccountService;
import com.chisw.banking.web.rest.AccountController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void shouldReturnUserAccountByEmail() throws Exception {
        String email = "chisw@mail.ru";

        Account account = Account.createNewActiveAccount();

        when(accountService.getUserAccountByEmail(any(String.class))).thenReturn(account);

        MvcResult mvcResult = mockMvc.perform(get("/api/accounts/" + email)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andReturn();

        assertNotNull(mvcResult.getResponse());

        ObjectMapper jsonMapper = new ObjectMapper();

        Account returnedAccount = jsonMapper.readValue(mvcResult.getResponse().getContentAsString(), Account.class);

        assertEquals(account.getBalance(), returnedAccount.getBalance());
        assertEquals(account.getStatus(), returnedAccount.getStatus());
        assertEquals(account.getCreateDate(), returnedAccount.getCreateDate());
    }
}
