package com.chisw.banking.service;

import com.chisw.banking.domain.Account;
import com.chisw.banking.domain.Operation;
import com.chisw.banking.domain.OperationStatus;
import com.chisw.banking.domain.User;
import com.chisw.banking.repository.UserRepository;
import com.chisw.banking.service.dto.OperationDto;
import com.chisw.banking.service.mapper.OperationMapper;
import com.chisw.banking.web.rest.errors.EmailNotFoundException;
import com.chisw.banking.web.rest.errors.OperationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final UserRepository userRepository;

    public void depositMoneyIntoTheAccount(String email, OperationDto operationDto) {
        Optional<User> oneByEmailIgnoreCase = userRepository.findOneByEmailIgnoreCase(email.toLowerCase());
        oneByEmailIgnoreCase.ifPresent(user -> performDeposit(user, operationDto));
    }

    public void withdrawMoneyFromAccount(String email, OperationDto operationDto) {
        Optional<User> oneByEmailIgnoreCase = userRepository.findOneByEmailIgnoreCase(email.toLowerCase());
        oneByEmailIgnoreCase.ifPresent(
                user -> performWithdraw(user, operationDto)
        );
    }

    private void performDeposit(User user, OperationDto operationDto) {
        if (operationDto.getActionWithBalance() > 0) {
            user.getAccount().setBalance(Double.sum(user.getAccount().getBalance(), operationDto.getActionWithBalance()));
            Operation operation = new OperationMapper().OperationDtoToOperation(operationDto);
            operation.setStatus(OperationStatus.SUCCESSFUL);
            operation.setTimestamp(new Date());
            user.getAccount().getAccountOperations().add(operation);
            userRepository.save(user);
        } else {
            Operation operation = new OperationMapper().OperationDtoToOperation(operationDto);
            operation.setStatus(OperationStatus.ERROR);
            operation.setTimestamp(new Date());
            user.getAccount().getAccountOperations().add(operation);
            userRepository.save(user);
            throw new OperationException("Can't perform deposit, requested amount should be positive", HttpStatus.BAD_REQUEST);
        }
    }

    private void performWithdraw(User user, OperationDto operationDto) {
        if (user.getAccount().getBalance() >= 0 && Double.sum(user.getAccount().getBalance(), operationDto.getActionWithBalance()) > 0) {
            user.getAccount().setBalance(Double.sum(user.getAccount().getBalance(), operationDto.getActionWithBalance()));
            Operation operation = new OperationMapper().OperationDtoToOperation(operationDto);
            operation.setStatus(OperationStatus.SUCCESSFUL);
            operation.setTimestamp(new Date());
            user.getAccount().getAccountOperations().add(operation);
            userRepository.save(user);
        } else {
            Operation operation = new OperationMapper().OperationDtoToOperation(operationDto);
            operation.setStatus(OperationStatus.ERROR);
            operation.setTimestamp(new Date());
            user.getAccount().getAccountOperations().add(operation);
            userRepository.save(user);
            throw new OperationException("Can't withdraw money, you don't have enough money for withdraw", HttpStatus.BAD_REQUEST);
        }
    }

    public Account getUserAccountByEmail(String email) {
        Optional<User> user = userRepository.findOneByEmailIgnoreCase(email);
        if (user.isPresent()) {
            return user.get().getAccount();
        } else {
            throw new EmailNotFoundException("User with email: " + email + "not found", HttpStatus.OK);
        }
    }
}

