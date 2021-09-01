package com.spring.mspursetransaction.service;

import com.spring.mspursetransaction.entity.PurseTransaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PurseTransactionService {
	Mono<PurseTransaction> create(PurseTransaction t);

    Flux<PurseTransaction> findAll();

    Mono<PurseTransaction> findById(String id);

    Mono<PurseTransaction> update(PurseTransaction t);

    Mono<Boolean> delete(String t);
}
