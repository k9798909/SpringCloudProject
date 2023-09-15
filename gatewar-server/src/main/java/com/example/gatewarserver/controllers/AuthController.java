package com.example.gatewarserver.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.gatewarserver.req.LoginReq;
import com.example.gatewarserver.req.TokenReq;
import com.example.gatewarserver.res.LoginRes;
import com.example.gatewarserver.services.UsersService;

import reactor.core.publisher.Mono;

@RestController
public class AuthController {
	private UsersService usersService;

	public AuthController(UsersService usersService) {
		super();
		this.usersService = usersService;
	}

	@PostMapping("/login")
	public Mono<ResponseEntity<LoginRes>> login(@RequestBody LoginReq req) {
		return usersService.login(req)
				.map(ResponseEntity::ok)
				.switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
	}

	@PostMapping("/tokenVerify")
	public Mono<ResponseEntity<Boolean>> tokenVerify(@RequestBody TokenReq req) {
		return usersService.tokenVerify(req).map(ResponseEntity::ok);
	}
}
