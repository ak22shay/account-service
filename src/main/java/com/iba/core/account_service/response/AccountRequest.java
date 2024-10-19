package com.iba.core.account_service.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountRequest {
    private String type;
    private int balance;
    private String currency;
    private String customerId;
}
