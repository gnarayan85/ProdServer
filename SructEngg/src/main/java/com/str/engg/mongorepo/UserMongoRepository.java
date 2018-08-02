package com.str.engg.mongorepo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.str.engg.model.User;

public interface UserMongoRepository extends MongoRepository<User, Long>{
		@Query("{ 'username' : ?0, 'enable': true }")
		User findByUsername(String username);
		User findByEnableCode(long enableCode);
}
