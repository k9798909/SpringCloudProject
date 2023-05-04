package com.example.gatewarserver.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.gatewarserver.repository.UserRepository;
import com.example.gatewarserver.utils.AuthJwtUtils;

import reactor.core.publisher.Mono;

@RestController
public class AuthController {

	private AuthJwtUtils authJwtUtils;
	private PasswordEncoder passwordEncoder;
	private UserRepository userRepository;

	public AuthController(AuthJwtUtils authJwtUtils, PasswordEncoder passwordEncoder, UserRepository userRepository) {
		super();
		this.authJwtUtils = authJwtUtils;
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	@PostMapping("/login")
	public Mono<ResponseEntity<AuthRes>> login(@RequestBody AuthReq req) {
		return userRepository.findByUsername(req.username)
				.filter(userDetails -> passwordEncoder.matches(req.password(),userDetails.getPassword()))
				.map(userDetails -> ResponseEntity.ok(new AuthRes(authJwtUtils.generate(userDetails.getUsername()))))
				.switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
	}
	
	@GetMapping("/hello")
	public ResponseEntity<String> hello() {
		return ResponseEntity.ok("working...");
	}

	public record AuthReq(String username, String password) {
	}
	
	public record AuthRes(String token) {
	}

}
