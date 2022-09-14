package com.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.user.models.User;

public interface UserRepository extends MongoRepository<User,String> {
	boolean existsByUserName(String userName);

	User findByUserName(String username);
	
	
    @Query("{'userName':?0, 'password':?1 }")
    public User login(String userName, String password);

}
