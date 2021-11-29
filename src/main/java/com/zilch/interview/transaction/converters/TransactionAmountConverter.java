package com.zilch.interview.transaction.converters;

import com.zilch.interview.transaction.models.TransactionType;
import com.zilch.interview.transaction.dtos.TransactionAmountDto;
import com.zilch.interview.transaction.models.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TransactionAmountConverter {

    TransactionAmountDto fromTransaction(Transaction transaction) {
        final TransactionType type = transaction.getType();
        final BigDecimal amount = (TransactionType.PAYOUT.equals(type)) ? transaction.getAmount().negate() : transaction.getAmount();
        final String currency = transaction.getCurrency();
        return new TransactionAmountDto(currency, amount);
    }
}
