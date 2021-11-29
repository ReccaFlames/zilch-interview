package com.zilch.interview.transaction.filters;

import lombok.Setter;

import java.time.Instant;

@Setter
public class TransactionFilterCriteriaBean {
    private Integer page;
    private Integer size;
    private Instant fromDate;
    private Instant toDate;

    public TransactionFilterCriteria getTransactionFilterCriteria() {
        return new TransactionFilterCriteria(page, size, fromDate, toDate);
    }
}
