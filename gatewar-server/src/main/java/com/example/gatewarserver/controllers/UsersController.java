package com.example.gatewarserver.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gatewarserver.req.SignUpReq;
import com.example.gatewarserver.services.UsersService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UsersController {
	private static final Logger log = LoggerFactory.getLogger(UsersController.class);

	private UsersService usersService;

	public UsersController(UsersService usersService) {
		super();
		this.usersService = usersService;
	}

	@PostMapping("/signUp")
	public Mono<ResponseEntity<Void>> add(@RequestBody SignUpReq req) {
		return usersService.save(req)
				.doOnError(e -> log.error("/users/signUp 發生錯誤", e))
				.<ResponseEntity<Void>>map(users -> ResponseEntity.ok().build())
				.onErrorReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build())
				;
	}
	
}
