package com.codefountain.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.codefountain.security.entity.CryptoCurrency;

@Repository
public interface CryptoCurrencyRepository extends MongoRepository<CryptoCurrency, String>{

	CryptoCurrency findBySymbol(String symbol);
	
}
