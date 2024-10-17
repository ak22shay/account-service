package com.iba.core.account_service.repository;

import com.iba.core.account_service.document.Account;
import com.iba.core.account_service.response.AccountResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRespository extends MongoRepository<Account, String> {
}
