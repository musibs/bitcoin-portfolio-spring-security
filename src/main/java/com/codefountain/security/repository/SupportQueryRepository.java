package com.codefountain.security.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.codefountain.security.entity.SupportQuery;

@Repository
public interface SupportQueryRepository extends MongoRepository<SupportQuery, String> {

	List<SupportQuery> findByUsername(String username);
	
}
