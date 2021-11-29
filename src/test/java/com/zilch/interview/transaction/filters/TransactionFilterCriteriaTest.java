package com.zilch.interview.transaction.filters;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionFilterCriteriaTest {

    @Test
    void shouldSupportEmptyFilterCriteria() {
        //given
        //when
        final TransactionFilterCriteria result = new TransactionFilterCriteria(null, null, null, null);

        //then
        assertThat(result.getPage()).isEmpty();
        assertThat(result.getSize()).isEmpty();
        assertThat(result.getFromDate()).isEmpty();
        assertThat(result.getToDate()).isEmpty();
    }

    @Test
    void shouldSupportAllFilterCriteria() {
        //given
        final int page = 2;
        final int size = 4;
        final Instant fromDate = Instant.parse("2020-03-02T02:03:12.00Z");
        final Instant toDate = Instant.parse("2020-03-03T02:03:12.00Z");
        //when
        final TransactionFilterCriteria result = new TransactionFilterCriteria(page, size, fromDate, toDate);

        //then
        assertThat(result.getPage()).hasValue(2);
        assertThat(result.getSize()).hasValue(4);
        assertThat(result.getFromDate()).hasValue(fromDate);
        assertThat(result.getToDate()).hasValue(toDate);
    }
}
