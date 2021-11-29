package com.zilch.interview.transaction.converters;

import com.zilch.interview.transaction.dtos.TransactionAmountDto;
import com.zilch.interview.transaction.models.Transaction;
import com.zilch.interview.transaction.models.TransactionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TransactionAmountConverterTest {

    @InjectMocks
    private TransactionAmountConverter transactionAmountConverter;

    @Test
    void shouldCreateTransactionAmountDtoFromPayInTransaction() {
        //given
        final String currency = "EUR";
        final BigDecimal amount = BigDecimal.valueOf(100);
        final Transaction transaction = new Transaction();
        transaction.setCurrency(currency);
        transaction.setAmount(amount);
        transaction.setType(TransactionType.PAYIN);
        final TransactionAmountDto expected = new TransactionAmountDto();
        expected.setCurrency(currency);
        expected.setAmount(amount);

        //when
        final TransactionAmountDto transactionAmountDto = transactionAmountConverter.fromTransaction(transaction);

        //then
        assertThat(transactionAmountDto).isEqualTo(expected);
    }

    @Test
    void shouldCreateTransactionAmountDtoFromPayOutTransaction() {
        //given
        final String currency = "EUR";
        final BigDecimal amount = BigDecimal.valueOf(100);
        final Transaction transaction = new Transaction();
        transaction.setCurrency(currency);
        transaction.setAmount(amount);
        transaction.setType(TransactionType.PAYOUT);
        final TransactionAmountDto expected = new TransactionAmountDto();
        expected.setCurrency(currency);
        expected.setAmount(amount.negate());

        //when
        final TransactionAmountDto transactionAmountDto = transactionAmountConverter.fromTransaction(transaction);

        //then
        assertThat(transactionAmountDto).isEqualTo(expected);
    }
}
