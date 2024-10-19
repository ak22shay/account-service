package com.iba.core.account_service.service;

import com.iba.core.account_service.document.Account;
import com.iba.core.account_service.repository.AccountRespository;
import com.iba.core.account_service.response.AccountRequest;
import com.iba.core.account_service.response.AccountResponse;
import com.iba.core.account_service.util.ObjectMapper;
import com.iba.core.account_service.util.exceptions.AccountNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@EnableCaching
@Slf4j
public class AccountService {

    @Autowired
    private AccountRespository accountRespository;
    @Cacheable(value = "accountCache", key = "#id")
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
    @Cacheable(value = "accountCache", key = "#id")
    public List<AccountResponse> getAllAccounts() {
        return accountRespository.findAll().stream()
                .map(ObjectMapper::mapAccountToAccountResponse)
                .collect(Collectors.toList());
    }

    public AccountResponse createAccount(AccountRequest accountRequest){
        Account account = ObjectMapper.mapAccountRequestToAccount(accountRequest);
        String accountId = accountIdGenerator();
        account.setId(accountId);

        accountRespository.save(account);
        return accountRespository.findById(accountId)
                .map(account1 -> {
                    log.info("New account created with id {}", accountId);
                    return ObjectMapper.mapAccountToAccountResponse(account1);
                })
                .orElseThrow(() -> {
                    log.error("Account cannot be created");
                    return new AccountNotFoundException("Account not found with ID: ");
                });
    }
    @CacheEvict(value = "accountCache")
    public AccountResponse deleteAccount(String id){
        AccountResponse accountResponse = getAccountDetails(id);
        accountRespository.deleteById(id);
        return accountResponse;
    }

    private String accountIdGenerator(){
        String id;
        do{
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                int digit = (int) (Math.random() * 10);
                builder.append(digit);
            }
            id =  builder.toString();
        } while(accountRespository.existsById(id));
        return id;
    }
}
