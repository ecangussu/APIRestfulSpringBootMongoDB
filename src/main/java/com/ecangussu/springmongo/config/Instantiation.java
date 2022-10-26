package com.ecangussu.springmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ecangussu.springmongo.domain.Post;
import com.ecangussu.springmongo.domain.User;
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

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		// Ao colocarmos o User no fina, estamos passando uma cópia e não uma referência
		// do objeto = coceito de objeto aninhado
		Post post1 = new Post(null, sdf.parse("25/10/2022"), "Jogo do título!",
				"Palmeiras pode ganhar seu 11º título brasileiro hoje!", estevao);
		Post post2 = new Post(null, sdf.parse("17/11/2013"), "20 anos!", "Hoje completo 20 anos!", estevao);

		userRepository.saveAll(Arrays.asList(estevao, alex, bob));
		postRepository.saveAll(Arrays.asList(post1, post2));

	}

}
