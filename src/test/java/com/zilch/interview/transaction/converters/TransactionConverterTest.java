package com.zilch.interview.transaction.converters;

import com.zilch.interview.transaction.dtos.TransactionAmountDto;
import com.zilch.interview.transaction.dtos.TransactionDto;
import com.zilch.interview.transaction.models.Transaction;
import com.zilch.interview.transaction.models.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionConverterTest {

    private TransactionAmountConverter transactionAmountConverter = new TransactionAmountConverter();
    private TransactionConverter transactionConverter;

    @BeforeEach
    void setUp() {
        transactionConverter = new TransactionConverter(transactionAmountConverter);
    }

    @Test
    void shouldReturnConvertedTransaction() {
        //given
        final UUID transactionId = UUID.fromString("dd2c4d33-0fb6-4300-9d4a-bd5482f51752");
        final UUID accountId = UUID.fromString("0ff9f942-8e98-488f-85ca-bb6c840dfa1b");
        final String currency = "EUR";
        final BigDecimal amount = BigDecimal.valueOf(100);
        final Instant bookingDate = Instant.parse("2020-03-02T02:03:12.00Z");
        final Instant valueDate = Instant.parse("2020-03-01T02:03:12.00Z");

        final Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionId);
        transaction.setCurrency(currency);
        transaction.setAmount(amount);
        transaction.setType(TransactionType.PAYIN);
        transaction.setAccountId(accountId);
        transaction.setBookingDate(bookingDate);
        transaction.setValueDate(valueDate);

        final TransactionAmountDto transactionAmountDto = new TransactionAmountDto(currency, amount);
        final TransactionDto expected = new TransactionDto();
        expected.setTransactionId(transactionId);
        expected.setAccountId(accountId);
        expected.setBookingDate(bookingDate);
        expected.setValueDate(valueDate);
        expected.setTransactionAmountDto(transactionAmountDto);

        //when
        final TransactionDto transactionDto = transactionConverter.fromTransaction(transaction);

        //then
        assertThat(transactionDto).isEqualTo(expected);
    }
}
