package com.iba.core.account_service.response;

import lombok.Data;

@Data
public class AccountResponse {

    private String id;
    private String type;
    private int balance;
    private String currency;
    private String customerId;
}
