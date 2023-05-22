package com.example.gatewarserver.utils;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class AuthJwtUtils {
	private static Logger log = LoggerFactory.getLogger(AuthJwtUtils.class);
	
	@Value("${jwt.key}")
	private String key;
    private long EXPIRE_TIME = 1 * 60 * 1000;
    private String issuer = "gateway";

    public String generate(String username) {
    	Algorithm algorithm = Algorithm.HMAC256(key);
    	String jwtToken = JWT.create()
    			  .withIssuer(issuer)
    			  .withSubject(username)
    			  //.withClaim("username", username)
    			  .withIssuedAt(new Date())
    			  .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
    			  .withJWTId(UUID.randomUUID().toString())
    			  .sign(algorithm);
    	return jwtToken;
    }

    public boolean verify(String token) {
    	try {
    		Algorithm algorithm = Algorithm.HMAC256(key);
    		JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
    		verifier.verify(token);
    	    return true;
    	} catch (JWTVerificationException e) {
			log.debug("verify exception", e);
	   	    return false;
    	}
    }

    public Optional<String> getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return Optional.of(jwt.getSubject().toString());
        } catch (JWTDecodeException e) {
        	log.debug("getUsername exception", e);
            return Optional.empty();
        }
    }
    
}
