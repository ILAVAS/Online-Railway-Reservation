package com.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.admin.pojo.Admin;

@Repository
public interface AdminRepository extends MongoRepository<Admin, Integer> {
	
//	The query on line 17, 20 and 23 are optional, but the query in line 26 is compulsory for checking 2 parameters
	
	@Query("{'phone':?0 }")  
	public Optional<Admin> findByPhone(long phone);
	
	@Query("{'userName':?0 }")
	public Optional<Admin> findByUserName(String userName);
	    
	@Query("{'location':?0 }")
	public List<Admin> findByLocation(String location);
		
    @Query("{'userName':?0, 'password':?1 }")
    public Admin login(String userName, String password);

}
