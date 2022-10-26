package com.ecangussu.springmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecangussu.springmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}