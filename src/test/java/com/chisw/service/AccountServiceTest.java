package com.chisw.service;

import com.chisw.AbstractTest;
import com.chisw.banking.domain.User;
import com.chisw.banking.repository.UserRepository;
import com.chisw.banking.service.AccountService;
import com.chisw.banking.service.dto.OperationDto;
import com.chisw.banking.web.rest.errors.OperationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class AccountServiceTest extends AbstractTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private UserRepository userRepository;

    private String email;

    private OperationDto operationDto;

    private Double moneyIn;

    private User user;

    @Before
    public void setup() {
        moneyIn = 0D;
        operationDto = new OperationDto();
        operationDto.setActionWithBalance(moneyIn);
        email = "chisw45@mail.ru";

        user = createUser();
        user.setAccount(createAccount());
    }

    @Test(expected = OperationException.class)
    public void performDeposit_shouldFailOnOperationException() {
        when(userRepository.findOneByEmailIgnoreCase(email)).thenReturn(Optional.of(user));
        accountService.depositMoneyIntoTheAccount(email, operationDto);
    }

    @Test
    public void performDeposit_shouldWork() {
        Double moneyIn = 1D;
        OperationDto operationDto = new OperationDto();
        operationDto.setActionWithBalance(moneyIn);

        when(userRepository.findOneByEmailIgnoreCase(email)).thenReturn(Optional.of(user));
        accountService.depositMoneyIntoTheAccount(email, operationDto);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void performWithDraw_shouldWork() {
        Double moneyIn = 1D;
        OperationDto operationDto = new OperationDto();
        operationDto.setActionWithBalance(moneyIn);

        when(userRepository.findOneByEmailIgnoreCase(email)).thenReturn(Optional.of(user));
        accountService.withdrawMoneyFromAccount(email, operationDto);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test(expected = OperationException.class)
    public void performWithDraw_shouldFailOnOperationException() {
        when(userRepository.findOneByEmailIgnoreCase(email)).thenReturn(Optional.of(user));
        accountService.withdrawMoneyFromAccount(email, operationDto);
    }
}
