package com.zilch.interview.account;

import com.zilch.interview.transaction.dtos.TransactionDto;
import com.zilch.interview.transaction.filters.TransactionFilterCriteria;
import com.zilch.interview.transaction.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class AccountFacade {
    private TransactionService transactionService;

    public Optional<List<TransactionDto>> getAllTransactionsByAccount(UUID accountId, TransactionFilterCriteria transactionFilterCriteria) {
        return transactionService.getAllTransactionsByAccount(accountId, transactionFilterCriteria);
    }
}
