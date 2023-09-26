package com.example.gatewarserver.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gatewarserver.model.Users;
import com.example.gatewarserver.repository.UserRepository;
import com.example.gatewarserver.req.EditUsersPostReq;
import com.example.gatewarserver.req.LoginReq;
import com.example.gatewarserver.req.SignUpReq;
import com.example.gatewarserver.req.TokenReq;
import com.example.gatewarserver.res.LoginRes;
import com.example.gatewarserver.utils.AuthJwtUtils;

import reactor.core.publisher.Mono;

@Service
public class UsersService {
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private AuthJwtUtils authJwtUtils;
	
	public UsersService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthJwtUtils authJwtUtils) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.authJwtUtils = authJwtUtils;
	}
	
	public Mono<LoginRes> login(LoginReq req) {
		return userRepository.findByUsername(req.username())
				.filter(userDetails -> passwordEncoder.matches(req.password(), userDetails.getPassword()))
				.map(userDetails -> (Users) userDetails)
				.map(users -> new LoginRes(users.getUsername(), 
						users.getName(),
						authJwtUtils.generateToken(users.getUsername()))
				);
	}
	
	public Mono<Boolean> tokenVerify(TokenReq req) {
		return Mono.just(authJwtUtils.validateToken(req.token()));
	}

	@Transactional
	public Mono<Users> save(SignUpReq req) {
		Users users = new Users();
		users.setUsername(req.username());
		users.setPassword(passwordEncoder.encode(req.password()));
		users.setName(req.name());
		users.setEmail(req.email());
		users.setBirthday(LocalDate.parse(req.birthday(),DateTimeFormatter.ISO_DATE));
		users.setAddress(req.address());
		return userRepository.save(users);
	}
	
	@Transactional
	public Mono<Users> update(EditUsersPostReq req) {
		return userRepository.findById(req.id()).map(users -> {
			users.setId(req.id());
			users.setName(req.name());
			users.setEmail(req.email());
			users.setBirthday(LocalDate.parse(req.birthday(),DateTimeFormatter.ISO_DATE));
			users.setAddress(req.address());
			return users;
		}).flatMap(users -> userRepository.save(users));
	}
	
	public Mono<Users> findBy(String username) {
		return userRepository.findByUsername(username);
	}
	
	public Mono<Users> findBy(String username,String authUsername) {
		if (StringUtils.equals(authUsername, username)) {
			throw new RuntimeException("tokenUsername and username not match");
		}
		
		return userRepository.findByUsername(username);
	}
}
