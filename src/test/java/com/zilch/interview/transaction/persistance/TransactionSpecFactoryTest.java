package com.zilch.interview.transaction.persistance;

import com.zilch.interview.transaction.models.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class TransactionSpecFactoryTest {

    @Test
    void shouldReturnSpecification() {
        //given
        final TransactionSpecFactory specFactory = new TransactionSpecFactory();

        //when
        final Specification<Transaction> specification = specFactory.createSpecification(UUID.fromString("49e7831b-54e5-4c66-9e00-e8154a5af201"), Instant.parse("2020-03-02T02:03:12.00Z"), Instant.parse("2020-03-03T02:03:12.00Z"));

        //then
        assertThat(specification).isNotNull();
    }
}
