package com.example.gatewarserver.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.gatewarserver.model.Users;

import reactor.core.publisher.Mono;

public interface UserRepository extends R2dbcRepository<Users, String> {
	Mono<Users> findByUsername(String username);
}
