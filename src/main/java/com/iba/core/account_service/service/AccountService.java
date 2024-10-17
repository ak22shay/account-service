package com.iba.core.account_service.service;

import com.iba.core.account_service.document.Account;
import com.iba.core.account_service.repository.AccountRespository;
import com.iba.core.account_service.response.AccountResponse;
import com.iba.core.account_service.util.ObjectMapper;
import com.iba.core.account_service.util.exceptions.AccountNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@EnableCaching
@Slf4j
public class AccountService {

    @Autowired
    private AccountRespository accountRespository;
    @Cacheable(cacheNames = "accountCache")
    public AccountResponse getAccountDetails(String id) {
        return accountRespository.findById(id)
                .map(account -> {
                    log.info("successfully fetched the account details for thr id {}", id);
                    return ObjectMapper.mapAccountToAccountResponse(account);
                })
                .orElseThrow(() -> {
                    log.error("Account not found with ID: {}", id);
                    return new AccountNotFoundException("Account not found with ID: " + id);
                });
    }

    public List<AccountResponse> getAllAccounts() {
        return accountRespository.findAll().stream()
                .map(ObjectMapper::mapAccountToAccountResponse)
                .collect(Collectors.toList());
    }
}
