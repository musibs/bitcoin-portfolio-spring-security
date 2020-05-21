package com.codefountain.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.codefountain.security.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	User findByUsername(String username);
	User findByEmail(String email);

}
