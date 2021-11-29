package com.zilch.interview.transaction.converters;

import com.zilch.interview.transaction.dtos.TransactionAmountDto;
import com.zilch.interview.transaction.dtos.TransactionDto;
import com.zilch.interview.transaction.models.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TransactionConverter {
    private final TransactionAmountConverter transactionAmountConverter;

    public TransactionDto fromTransaction(Transaction transaction) {
        final UUID transactionId = transaction.getTransactionId();
        final UUID accountId = transaction.getAccountId();
        final TransactionAmountDto transactionAmountDto = transactionAmountConverter.fromTransaction(transaction);
        final Instant bookingDate = transaction.getBookingDate();
        final Instant valueDate = transaction.getValueDate();
        return new TransactionDto(transactionId, accountId, transactionAmountDto, bookingDate, valueDate);
    }
}
