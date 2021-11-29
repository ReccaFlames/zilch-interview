package com.zilch.interview.transaction.persistance;

import com.zilch.interview.transaction.models.Transaction;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.util.UUID;

class TransactionSpecification {

    private TransactionSpecification() {
    }

    static Specification<Transaction> accountIdEqualTo(UUID accountId) {
        return (root, query, criteriaBuilder) -> accountId == null ? null : criteriaBuilder.equal(root.get("accountId"), accountId);
    }

    static Specification<Transaction> valueDateAfter(Instant after) {
        return ((root, query, criteriaBuilder) -> after == null ? null : criteriaBuilder.greaterThan(root.get("valueDate"), after));
    }

    static Specification<Transaction> valueDateBefore(Instant before) {
        return ((root, query, criteriaBuilder) -> before == null ? null : criteriaBuilder.lessThan(root.get("valueDate"), before));
    }
}
