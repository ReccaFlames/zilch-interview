package com.zilch.interview.transaction.filters;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionFilterCriteriaBeanTest {

    @Test
    void shouldCreateFilterCriteriaWithDefaultConstructorAndSetters() {
        //given
        final Instant fromDate = Instant.parse("2020-03-02T02:03:12.00Z");
        final Instant toDate = Instant.parse("2020-03-03T02:03:12.00Z");
        final TransactionFilterCriteriaBean transactionFilterCriteriaBean = new TransactionFilterCriteriaBean();
        transactionFilterCriteriaBean.setFromDate(fromDate);
        transactionFilterCriteriaBean.setToDate(toDate);
        transactionFilterCriteriaBean.setPage(2);
        transactionFilterCriteriaBean.setSize(4);

        //when
        final TransactionFilterCriteria result = transactionFilterCriteriaBean.getTransactionFilterCriteria();

        //then
        assertThat(result).isEqualTo(new TransactionFilterCriteria(2, 4, fromDate, toDate));
    }
}
