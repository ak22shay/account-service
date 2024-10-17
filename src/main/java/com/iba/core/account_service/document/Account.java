package com.iba.core.account_service.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@Document(collection = "accounts")
public class Account {
    @Id
    private String id;
    private String type;
    private int balance;
    private String currency;
    private String customerId;

}
