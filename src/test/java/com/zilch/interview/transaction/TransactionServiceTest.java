package com.zilch.interview.transaction;

import com.zilch.interview.transaction.converters.TransactionConverter;
import com.zilch.interview.transaction.dtos.TransactionDto;
import com.zilch.interview.transaction.filters.TransactionFilterCriteria;
import com.zilch.interview.transaction.models.Transaction;
import com.zilch.interview.transaction.persistance.TransactionSpecFactory;
import com.zilch.interview.transaction.repositories.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {
    @Mock
    private TransactionRepository repository;
    @Mock
    private TransactionSpecFactory specificationFactory;
    @Mock
    private TransactionConverter transactionConverter;
    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private Specification<Transaction> specification;
    @Mock
    private Page<Transaction> transactionPage;
    @Mock
    private Transaction transaction;
    @Mock
    private TransactionDto transactionDto;

    @Test
    void shouldReturnAllTransactionsForAccountAndDefaultSearchCriteria() {
        //given
        final UUID accountId = UUID.fromString("1405b7d7-a1f5-4aff-bdff-2c37ec9fad3a");
        final TransactionFilterCriteria transactionFilterCriteria = new TransactionFilterCriteria(null, null, null, null);
        final PageRequest pageRequest = PageRequest.of(0, 100, Sort.Direction.DESC, "valueDate");
        given(specificationFactory.createSpecification(accountId, null, null)).willReturn(specification);
        given(repository.findAll(specification, pageRequest)).willReturn(transactionPage);
        given(transactionPage.getContent()).willReturn(List.of(transaction));
        given(transactionConverter.fromTransaction(transaction)).willReturn(transactionDto);

        //when
        final Optional<List<TransactionDto>> transactions = transactionService.getAllTransactionsByAccount(accountId, transactionFilterCriteria);

        //then
        assertThat(transactions).hasValue(List.of(transactionDto));
    }

    @Test
    void shouldReturnAllTransactionsForAccountAndAllFilterCriteria() {
        //given
        final UUID accountId = UUID.fromString("1405b7d7-a1f5-4aff-bdff-2c37ec9fad3a");
        final int page = 1;
        final int size = 10;
        final Instant fromDate = Instant.parse("2020-03-01T02:03:12.00Z");
        final Instant toDate = Instant.parse("2020-03-02T02:03:12.00Z");
        final TransactionFilterCriteria transactionFilterCriteria = new TransactionFilterCriteria(page, size, fromDate, toDate);
        final PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.DESC, "valueDate");
        given(specificationFactory.createSpecification(accountId, fromDate, toDate)).willReturn(specification);
        given(repository.findAll(specification, pageRequest)).willReturn(transactionPage);
        given(transactionPage.getContent()).willReturn(List.of(transaction));
        given(transactionConverter.fromTransaction(transaction)).willReturn(transactionDto);

        //when
        final Optional<List<TransactionDto>> transactions = transactionService.getAllTransactionsByAccount(accountId, transactionFilterCriteria);

        //then
        assertThat(transactions).hasValue(List.of(transactionDto));
    }
}
