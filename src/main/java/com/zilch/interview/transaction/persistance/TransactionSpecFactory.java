package com.zilch.interview.transaction.persistance;

import com.zilch.interview.transaction.models.Transaction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

import static com.zilch.interview.transaction.persistance.TransactionSpecification.*;

@Component
public class TransactionSpecFactory {

    public Specification<Transaction> createSpecification(UUID accountId, Instant fromDate, Instant toDate) {
        return Specification.where(accountIdEqualTo(accountId))
                .and(valueDateAfter(fromDate))
                .and(valueDateBefore(toDate));
    }
}
