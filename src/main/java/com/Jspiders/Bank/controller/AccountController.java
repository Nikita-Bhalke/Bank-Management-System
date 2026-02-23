package com.Jspiders.Bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Jspiders.Bank.ResponseDto.AccountDto;
import com.Jspiders.Bank.service.IAccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping("/{bankId}")
    public ResponseEntity<AccountDto> createAccount(
            @PathVariable Long bankId,
            @RequestBody AccountDto dto) {

        dto.setBankId(bankId);
        return new ResponseEntity<>(accountService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(
            @PathVariable Long id,
            @RequestBody AccountDto dto) {
        return ResponseEntity.ok(accountService.updateAccount(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully");
    }

    @GetMapping("/bank/{bankId}")
    public ResponseEntity<List<AccountDto>> getAccountsByBankId(
            @PathVariable Long bankId) {
        return ResponseEntity.ok(accountService.getAccountByBankId(bankId));
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(
            @PathVariable Long id,
            @RequestParam double amount) {
        return ResponseEntity.ok(accountService.deposite(id, amount));
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(
            @PathVariable Long id,
            @RequestParam double amount) {
        return ResponseEntity.ok(accountService.withdraw(id, amount));
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<Double> getBalance(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getBalance(id));
    }
}
