package com.zilch.interview.transaction.filters;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.Instant;
import java.util.Optional;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class TransactionFilterCriteria {

    private final Integer page;
    private final Integer size;
    private final Instant fromDate;
    private final Instant toDate;

    public Optional<Integer> getPage() {
        return Optional.ofNullable(page);
    }

    public Optional<Integer> getSize() {
        return Optional.ofNullable(size);
    }

    public Optional<Instant> getFromDate() {
        return Optional.ofNullable(fromDate);
    }

    public Optional<Instant> getToDate() {
        return Optional.ofNullable(toDate);
    }
}
