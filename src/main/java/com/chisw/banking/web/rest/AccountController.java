package com.chisw.banking.web.rest;

import com.chisw.banking.domain.Account;
import com.chisw.banking.security.SecurityUtils;
import com.chisw.banking.service.AccountService;
import com.chisw.banking.service.dto.OperationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/accounts/deposit")
    public ResponseEntity<String> depositMoneyIntoTheAccount(@RequestBody OperationDto operationDto) {
        Optional<String> email = SecurityUtils.getCurrentUserLogin();
        email.ifPresent(e -> accountService.depositMoneyIntoTheAccount(e, operationDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/accounts/withdraw")
    public ResponseEntity<String> withdrawMoneyFromAccount(@RequestBody OperationDto operationDto) {
        Optional<String> email = SecurityUtils.getCurrentUserLogin();
        email.ifPresent(e -> accountService.withdrawMoneyFromAccount(e, operationDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/accounts/{email}")
    public ResponseEntity<Account> getUserAccountByEmail(@PathVariable String email) {
        Account account = accountService.getUserAccountByEmail(email);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
