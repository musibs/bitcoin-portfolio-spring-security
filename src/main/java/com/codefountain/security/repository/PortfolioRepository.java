package com.codefountain.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.codefountain.security.entity.Portfolio;

@Repository
public interface PortfolioRepository extends MongoRepository<Portfolio, String> {
	
	Portfolio findByUserName(String userName);
	
}
