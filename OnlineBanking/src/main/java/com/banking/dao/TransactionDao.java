package com.banking.dao;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransactionDao {
    private Long fromAccountId;
    private Long toAccountId;
    private BigDecimal amount;
    private String description;
}
