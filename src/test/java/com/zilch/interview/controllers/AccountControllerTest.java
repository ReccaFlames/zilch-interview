package com.zilch.interview.controllers;

import com.zilch.interview.account.AccountFacade;
import com.zilch.interview.transaction.dtos.TransactionDto;
import com.zilch.interview.transaction.filters.TransactionFilterCriteria;
import com.zilch.interview.transaction.filters.TransactionFilterCriteriaBean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @Mock
    private AccountFacade accountFacade;
    @InjectMocks
    private AccountController accountController;

    @Mock
    private TransactionFilterCriteriaBean transactionFilterCriteriaBean;
    @Mock
    private TransactionFilterCriteria transactionFilterCriteria;
    @Mock
    private TransactionDto transactionDto;

    @Test
    void shouldReturnHttpOkWhenTransactionsExistForAccount() {
        //given
        final UUID id = UUID.fromString("663c9bc7-061b-4470-86d0-ba089a236b4c");
        given(transactionFilterCriteriaBean.getTransactionFilterCriteria()).willReturn(transactionFilterCriteria);
        given(accountFacade.getAllTransactionsByAccount(id, transactionFilterCriteria)).willReturn(Optional.of(List.of(transactionDto)));

        //when
        final ResponseEntity response = accountController.getAllTransactions(id, transactionFilterCriteriaBean);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(List.of(transactionDto));
    }

    @Test
    void shouldReturnHttpNotFoundWhenTransactionNotExistForAccount() {
        //given
        final UUID id = UUID.fromString("73346398-1ab6-4c1b-94ef-4060fe7feec2");
        given(transactionFilterCriteriaBean.getTransactionFilterCriteria()).willReturn(transactionFilterCriteria);
        given(accountFacade.getAllTransactionsByAccount(id, transactionFilterCriteria)).willReturn(Optional.empty());

        //when
        final ResponseEntity response = accountController.getAllTransactions(id, transactionFilterCriteriaBean);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isEqualTo(List.of());
    }
}
