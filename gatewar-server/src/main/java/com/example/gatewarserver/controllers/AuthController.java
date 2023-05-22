package com.example.gatewarserver.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.gatewarserver.model.Users;
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
	public Mono<ResponseEntity<LoginRes>> login(@RequestBody LoginReq req) {
		return userRepository.findByUsername(req.username)
				.filter(userDetails -> passwordEncoder.matches(req.password(), userDetails.getPassword()))
				.map(userDetails -> (Users) userDetails)
				.map(users -> ResponseEntity
						.ok(new LoginRes(users.getName(), authJwtUtils.generate(users.getUsername()))))
				.switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
	}

	@PostMapping("/getUsername")
	public Mono<ResponseEntity<GetUsernameRes>> getUsername(@RequestBody TokenReq req) {
		String username = authJwtUtils.getUsername(req.token)
				.orElseThrow(() -> new RuntimeException("此 token 無法解析成username"));
		GetUsernameRes res = new GetUsernameRes(username);
		return Mono.just(ResponseEntity.ok(res));
	}
	
	@PostMapping("/tokenVerify")
	public Mono<ResponseEntity<Boolean>> tokenVerify(@RequestBody TokenReq req) {
		ResponseEntity<Boolean> res = ResponseEntity.ok(authJwtUtils.verify(req.token));
		return Mono.just(res);
	}

	@GetMapping("/hello")
	public ResponseEntity<String> hello() {
		return ResponseEntity.ok("working...");
	}

	public record LoginReq(String username, String password) {
	}

	public record LoginRes(String name, String token) {
	}

	public record TokenReq(String token) {
	}

	public record GetUsernameRes(String username) {
	}

}
