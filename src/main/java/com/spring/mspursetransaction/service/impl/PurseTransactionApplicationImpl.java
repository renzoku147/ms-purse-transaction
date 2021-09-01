package com.spring.mspursetransaction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mspursetransaction.entity.PurseTransaction;
import com.spring.mspursetransaction.repository.PurseTransactionRepository;
import com.spring.mspursetransaction.service.PurseTransactionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PurseTransactionApplicationImpl implements PurseTransactionService{

	@Autowired
	private PurseTransactionRepository purseTransactionRepository;
	
	@Override
	public Mono<PurseTransaction> create(PurseTransaction t) {
		return purseTransactionRepository.save(t);
	}

	@Override
	public Flux<PurseTransaction> findAll() {
		return purseTransactionRepository.findAll();
	}

	@Override
	public Mono<PurseTransaction> findById(String id) {
		return purseTransactionRepository.findById(id);
	}

	@Override
	public Mono<PurseTransaction> update(PurseTransaction t) {
		return purseTransactionRepository.save(t);
	}

	@Override
	public Mono<Boolean> delete(String t) {
		return purseTransactionRepository.findById(t)
                .flatMap(ptr -> purseTransactionRepository.delete(ptr)
                				.then(Mono.just(Boolean.TRUE)))
                .defaultIfEmpty(Boolean.FALSE);
	}

}
