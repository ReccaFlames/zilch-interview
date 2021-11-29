package com.zilch.interview.transaction.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private UUID transactionId;
    private UUID accountId;
    private TransactionAmountDto transactionAmountDto;
    private Instant bookingDate;
    private Instant valueDate;
}
