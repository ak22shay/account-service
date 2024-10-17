package com.iba.core.account_service.util.exceptions;

import lombok.Data;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(String message){
        super(message);
    }
}
