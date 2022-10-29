package com.ecangussu.springmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecangussu.springmongo.domain.Post;
import com.ecangussu.springmongo.repositories.PostRepository;
import com.ecangussu.springmongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public Post findById(String id) {
		Optional<Post> post = postRepository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Post não encontrado!"));
	}

	public List<Post> findByTitle(String text) {
		return postRepository.searchTitle(text);
	}

	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		return postRepository.fullSearch(text, minDate, maxDate);
	}

}
