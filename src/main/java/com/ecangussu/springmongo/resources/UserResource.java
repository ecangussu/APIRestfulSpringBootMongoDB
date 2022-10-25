package com.ecangussu.springmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecangussu.springmongo.domain.User;
import com.ecangussu.springmongo.dto.UserDTO;
import com.ecangussu.springmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET) // @GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> listUsers = userService.findAll();
		List<UserDTO> listUsersDTO = listUsers.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listUsersDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User user = userService.findById(id);
		UserDTO userDTO = new UserDTO(user);
		return ResponseEntity.ok().body(userDTO);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO) {
		User user = userService.fromDTO(userDTO);
		user = userService.insert(user);
		// URL do novo recurso criado (endereço/localização do novo usuário/objeto inserido)
		// Exibida no cabeçalho da requisição
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		// Reposta vazia (void), código 201 (created) e com cabeçalho (uri)
		return ResponseEntity.created(uri).build();
	}

}
