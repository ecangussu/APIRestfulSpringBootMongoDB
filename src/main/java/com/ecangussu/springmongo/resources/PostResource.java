package com.ecangussu.springmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecangussu.springmongo.domain.Post;
import com.ecangussu.springmongo.resources.util.URL;
import com.ecangussu.springmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService postService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		return ResponseEntity.ok().body(postService.findById(id));
	}

	@GetMapping(value = "/titlesearch")
	// RequestParam -> quando usamos o padrão ? ao invés de / no endpoint
	// value="text" -> text é o valor usando no endpoint depois do ?
	// defaultValue -> se não for colocado text será colocado "" no lugar
	// exemplo: /posts?text=string
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
		text = URL.decodeParam(text);
		return ResponseEntity.ok().body(postService.findByTitle(text));
	}

}
