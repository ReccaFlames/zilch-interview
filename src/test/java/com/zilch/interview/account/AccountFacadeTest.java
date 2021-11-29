package com.zilch.interview.account;

import com.zilch.interview.transaction.TransactionService;
import com.zilch.interview.transaction.dtos.TransactionDto;
import com.zilch.interview.transaction.filters.TransactionFilterCriteria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AccountFacadeTest {

    @Mock
    private TransactionService transactionService;
    @InjectMocks
    private AccountFacade accountFacade;

    @Mock
    private TransactionFilterCriteria transactionFilterCriteria;
    @Mock
    private TransactionDto transactionDto;

    @Test
    void shouldReturnTransactions() {
        //given
        final UUID accountId = UUID.fromString("3e821505-4e82-4164-8582-91652a53579e");
        given(transactionService.getAllTransactionsByAccount(accountId, transactionFilterCriteria)).willReturn(Optional.of(List.of(transactionDto)));

        //when
        final Optional<List<TransactionDto>> result = accountFacade.getAllTransactionsByAccount(accountId, transactionFilterCriteria);

        //then
        assertThat(result).isNotEmpty();
    }
}
