package com.zilch.interview.controllers;

import com.zilch.interview.account.AccountFacade;
import com.zilch.interview.transaction.dtos.TransactionDto;
import com.zilch.interview.transaction.filters.TransactionFilterCriteriaBean;
import lombok.AllArgsConstructor;
import org.owasp.encoder.Encode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    private final AccountFacade accountFacade;

    @GetMapping("/{id}/transactions")
    public ResponseEntity getAllTransactions(@PathVariable UUID id, TransactionFilterCriteriaBean filterCriteriaBean) {
        final var transactionFilterCriteria = filterCriteriaBean.getTransactionFilterCriteria();
        LOGGER.info("Access Transactions for account: {}", Encode.forJava(id.toString()));
        final Optional<List<TransactionDto>> transactions = accountFacade.getAllTransactionsByAccount(id, transactionFilterCriteria);
        if (transactions.isPresent()) {
            return new ResponseEntity<>(transactions.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }

    }
}
