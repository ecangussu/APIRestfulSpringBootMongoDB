package com.ecangussu.springmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecangussu.springmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	//MongoRepository -> quando trabalhamos com o mongo extendemos dessa interface
	//User = entidade que vamos trabalhar
	//String = tipo do Id da entidade que vamos trabalhar
	
}
