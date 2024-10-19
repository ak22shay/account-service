package com.iba.core.account_service.util;

import com.iba.core.account_service.document.Account;
import com.iba.core.account_service.response.AccountRequest;
import com.iba.core.account_service.response.AccountResponse;
import org.modelmapper.ModelMapper;



public class ObjectMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static AccountResponse  mapAccountToAccountResponse(Account account) {
        return modelMapper.map(account, AccountResponse.class);
    }

    public static Account mapAccountRequestToAccount(AccountRequest accountRequest){
        return modelMapper.map(accountRequest, Account.class);
    }
}
