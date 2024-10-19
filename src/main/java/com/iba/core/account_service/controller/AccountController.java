package com.iba.core.account_service.controller;

import com.iba.core.account_service.response.AccountRequest;
import com.iba.core.account_service.response.AccountResponse;
import com.iba.core.account_service.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/accounts")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable String id){
        log.info("GET account details API called for the account id {}",id);
        return ResponseEntity.ok(accountService.getAccountDetails(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountResponse>> getAccount(){
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @PostMapping("/create")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest accountRequest){
        log.info("New account creation request with details : {}", accountRequest.toString());
        return ResponseEntity.ok(accountService.createAccount(accountRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AccountResponse> deleteAccount(@PathVariable String id){
        log.info("DELETE account details API called for the account id {}",id);
        return ResponseEntity.ok(accountService.deleteAccount(id));
    }
}
