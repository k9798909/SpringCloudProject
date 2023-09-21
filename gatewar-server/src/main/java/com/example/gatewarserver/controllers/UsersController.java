package com.example.gatewarserver.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.gatewarserver.req.SignUpReq;
import com.example.gatewarserver.services.UsersService;

import reactor.core.publisher.Mono;

@RestController
public class UsersController {
	private static final Logger log = LoggerFactory.getLogger(UsersController.class);

	private UsersService usersService;

	public UsersController(UsersService usersService) {
		super();
		this.usersService = usersService;
	}
	
	@GetMapping("/public/users/checkUsername")
	public Mono<ResponseEntity<Boolean>> checkUsername(@RequestParam String username) {
		return usersService.findBy(username)
				.map(users -> ResponseEntity.ok(false))
				.switchIfEmpty(Mono.just(ResponseEntity.ok(true)))
				.doOnError(e -> log.error("/public/users/checkUsername 發生錯誤", e))
				;
	}

	@PostMapping("/public/users/signUp")
	public Mono<ResponseEntity<Void>> signUp(@RequestBody SignUpReq req) {
		return usersService.save(req)
				.doOnError(e -> log.error("/users/signUp 發生錯誤", e))
				.<ResponseEntity<Void>>map(users -> ResponseEntity.ok().build())
				.onErrorReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build())
				;
	}
	
}
