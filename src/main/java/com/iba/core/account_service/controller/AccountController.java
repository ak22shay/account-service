package com.iba.core.account_service.controller;

import com.iba.core.account_service.document.Account;
import com.iba.core.account_service.response.AccountResponse;
import com.iba.core.account_service.service.AccountService;
import jakarta.ws.rs.Produces;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
