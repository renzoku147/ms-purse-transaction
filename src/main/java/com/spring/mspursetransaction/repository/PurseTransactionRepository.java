package com.spring.mspursetransaction.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.spring.mspursetransaction.entity.PurseTransaction;

public interface PurseTransactionRepository extends ReactiveMongoRepository<PurseTransaction, String>{

}
