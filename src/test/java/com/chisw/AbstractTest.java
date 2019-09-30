package com.chisw;

import com.chisw.banking.domain.Account;
import com.chisw.banking.domain.Status;
import com.chisw.banking.domain.User;
import com.chisw.banking.service.dto.UserDTO;
import com.chisw.banking.service.mapper.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;

public abstract class AbstractTest {
    protected String createJsonFromObject(Object source) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String result;
        try {
            result = objectWriter.writeValueAsString(source);
            if (source instanceof String) {
                result = result.replace("\"", StringUtils.EMPTY);
            }
        } catch (JsonProcessingException e) {
            result = null;
        }
        return result;
    }

    public UserDTO createUserDto() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("igor");
        userDTO.setSurName("l");
        userDTO.setPhone("1112223344");
        userDTO.setPassword("12341234");
        userDTO.setEmail("chisw45@yandex.ru");
        userDTO.setStatus(Status.ACTIVE);
        return userDTO;
    }

    protected Account createAccount() {
        Account account = new Account();
        account.setBalance(0.0);
        account.setStatus(Status.ACTIVE);
        account.setCreate_date(new Date());
        account.setAccountOperations(new ArrayList<>());
        return account;
    }

    public User createUser() {
        return new UserMapper().userDTOToUser(createUserDto());
    }
}
