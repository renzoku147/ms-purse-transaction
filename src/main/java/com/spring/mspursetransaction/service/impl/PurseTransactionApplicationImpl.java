package com.spring.mspursetransaction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.spring.mspursetransaction.entity.PurseTransaction;
import com.spring.mspursetransaction.repository.PurseTransactionRepository;
import com.spring.mspursetransaction.service.PurseTransactionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import redis.config.CacheConfig;

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
	@Cacheable(cacheNames = CacheConfig.USER_CACHE, unless = "#result == null")
	public Mono<PurseTransaction> findById(String id) {
		return purseTransactionRepository.findById(id);
	}

	@Override
	@CachePut(cacheNames = CacheConfig.USER_CACHE, key = "#t", unless = "#result == null")
	public Mono<PurseTransaction> update(PurseTransaction t) {
		return purseTransactionRepository.save(t);
	}

	@Override
	@CacheEvict(cacheNames = CacheConfig.USER_CACHE, key = "#t")
	public Mono<Boolean> delete(String t) {
		return purseTransactionRepository.findById(t)
                .flatMap(ptr -> purseTransactionRepository.delete(ptr)
                				.then(Mono.just(Boolean.TRUE)))
                .defaultIfEmpty(Boolean.FALSE);
	}

}
