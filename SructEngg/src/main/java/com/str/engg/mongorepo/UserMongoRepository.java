package com.str.engg.mongorepo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.str.engg.model.User;

public interface UserMongoRepository extends MongoRepository<User, Long>{
		User findByUsername(String username);

}
