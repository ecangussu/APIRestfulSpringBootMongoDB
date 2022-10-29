package com.ecangussu.springmongo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecangussu.springmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	public List<Post> searchTitle(String text);
	// @Query = permite realizarmos consultas do mongodb na forma de texto json
	// title representa o campo que iremos trabalhar
	// ?0 representa o primeiro campo passado como parâmetro
	// i representa o case insensitive (ignorar maiúsculo e minúsculo)

	public List<Post> findByTitleContainingIgnoreCase(String text);

	@Query("{ $and: [ { date: { $gte: ?1 } }, { date: { $lte: ?2 } }, "
			+ "{ $or: [ { 'title': { $regex: ?0, $options: 'i' } }, "
			+ "{ 'body': { $regex: ?0, $options: 'i' } }, "
			+ "{ 'comments.comment': { $regex: ?0, $options: 'i' } } ] } ] } ")
	public List<Post> fullSearch(String text, Date minDate, Date maxDate);

}