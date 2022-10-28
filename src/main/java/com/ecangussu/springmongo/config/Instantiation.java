package com.ecangussu.springmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ecangussu.springmongo.domain.Post;
import com.ecangussu.springmongo.domain.User;
import com.ecangussu.springmongo.dto.AuthorDTO;
import com.ecangussu.springmongo.dto.CommentDTO;
import com.ecangussu.springmongo.repositories.PostRepository;
import com.ecangussu.springmongo.repositories.UserRepository;

//Operação de instanciação/carga inicial da base de dados
@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll(); // vai limpar/zerar a collection do mongodb pra depois adicionar os novos
		postRepository.deleteAll();

		User estevao = new User(null, "Estevao Souza", "estevao@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(estevao, alex, bob));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		Post post1 = new Post(null, sdf.parse("25/10/2022"), "Jogo do título!",
				"Palmeiras pode ganhar seu 11º título brasileiro hoje!", new AuthorDTO(estevao));
		Post post2 = new Post(null, sdf.parse("17/11/2022"), "29 anos!", "Hoje farei 29 anos!",
				new AuthorDTO(estevao));
		
		CommentDTO comment1 = new CommentDTO("É isso aí, vai parmera!", sdf.parse("26/10/2022"), new AuthorDTO(alex));
		CommentDTO comment2 = new CommentDTO("Vai sonhando...", sdf.parse("26/10/2022"), new AuthorDTO(bob));
		CommentDTO comment3 = new CommentDTO("Parabéns, velhote!", sdf.parse("26/10/2022"), new AuthorDTO(bob));
		
		post1.getComments().addAll(Arrays.asList(comment1, comment2));
		post2.getComments().addAll(Arrays.asList(comment3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));

		estevao.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(estevao);

	}

}
