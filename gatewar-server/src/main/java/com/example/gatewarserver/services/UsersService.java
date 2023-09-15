package com.example.gatewarserver.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gatewarserver.model.Users;
import com.example.gatewarserver.repository.UserRepository;
import com.example.gatewarserver.req.SignUpReq;
import com.example.gatewarserver.req.LoginReq;
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
		users.setBirthday(req.birthday());
		users.setAddress(req.address());
		return userRepository.save(users);
	}
}
