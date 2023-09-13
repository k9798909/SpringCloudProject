package com.example.gatewarserver.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gatewarserver.model.Users;
import com.example.gatewarserver.repository.UserRepository;
import com.example.gatewarserver.req.AddUsersReq;

import reactor.core.publisher.Mono;

@Service
public class UsersService {
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	public UsersService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	public Mono<Users> save(AddUsersReq req) {
		Users users = new Users();
		users.setUsername(req.username());
		users.setPassword(passwordEncoder.encode(req.password()));
		users.setName(req.name());
		users.setEmail(req.email());
		users.setBirthday(req.birthday());
		users.setAddress(req.address());
		return userRepository.save(users);
	}
}
