package com.example.gatewarserver.utils;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class AuthJwtUtils {
	private final Key SECRET_KEY;
	private static final long EXPIRATION_TIME = 60 * 60 * 1000;
	
	public AuthJwtUtils(@Value("${jwt.key}") String key) {
		this.SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
	}

	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("username", username);
		
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(new Date(Instant.now().toEpochMilli() + EXPIRATION_TIME))
				.signWith(SECRET_KEY,SignatureAlgorithm.HS256)
				.compact();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
	                .setSigningKey(SECRET_KEY)
	                .build()
	                .parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		} 
	}
	
	public Optional<String> getUsername(String token) {
		try {
			Claims claims = Jwts.parserBuilder()
	                .setSigningKey(SECRET_KEY)
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
			return Optional.ofNullable(claims.get("username")).map(Object::toString);
		} catch (Exception e) {
			return Optional.empty();
		} 
	}
    
}
