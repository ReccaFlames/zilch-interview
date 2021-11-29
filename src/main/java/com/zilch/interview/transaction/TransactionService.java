package com.zilch.interview.transaction;

import com.zilch.interview.transaction.converters.TransactionConverter;
import com.zilch.interview.transaction.models.Transaction;
import com.zilch.interview.transaction.dtos.TransactionDto;
import com.zilch.interview.transaction.filters.TransactionFilterCriteria;
import com.zilch.interview.transaction.persistance.TransactionSpecFactory;
import com.zilch.interview.transaction.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;
    private final TransactionSpecFactory specificationFactory;
    private final TransactionConverter transactionConverter;

    public Optional<List<TransactionDto>> getAllTransactionsByAccount(UUID accountId, TransactionFilterCriteria transactionFilterCriteria) {
        final PageRequest pageRequest = createPageRequest(transactionFilterCriteria);
        final Specification<Transaction> specification = createSpecification(accountId, transactionFilterCriteria);
        final List<Transaction> content = repository.findAll(specification, pageRequest).getContent();
        final List<TransactionDto> transactionDtos = content.stream()
                .map(transactionConverter::fromTransaction)
                .collect(Collectors.toList());
        return Optional.of(transactionDtos);
    }

    private PageRequest createPageRequest(TransactionFilterCriteria filterCriteria) {
        final Integer page = filterCriteria.getPage().orElse(0);
        final Integer size = filterCriteria.getSize().orElse(100);
        return PageRequest.of(page, size, Sort.Direction.DESC, "valueDate");
    }

    private Specification<Transaction> createSpecification(UUID accountId, TransactionFilterCriteria filterCriteria) {
        final Instant fromDate = filterCriteria.getFromDate().orElse(null);
        final Instant toDate = filterCriteria.getToDate().orElse(null);
        return specificationFactory.createSpecification(accountId, fromDate, toDate);
    }
}
