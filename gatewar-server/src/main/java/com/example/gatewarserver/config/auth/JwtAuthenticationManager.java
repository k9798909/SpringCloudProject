package com.example.gatewarserver.config.auth;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.example.gatewarserver.utils.AuthJwtUtils;

import reactor.core.publisher.Mono;

/**
 * <p>負責驗證使用者身份，判斷使用者是否有權限訪問某些資源</p>
 * <p>當有請求需要進行身份驗證時會被調用</p>
 * 
 * @author k9798909
 *
 */
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {
	private AuthJwtUtils authJwtUtils;
	
	public JwtAuthenticationManager(AuthJwtUtils authJwtUtils) {
		this.authJwtUtils = authJwtUtils;
	}

	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		Optional<String> jwt = Optional.ofNullable(authentication.getCredentials()).map(Object::toString);
		if (jwt.isEmpty()) {
			return Mono.empty();
		}
		if (!authJwtUtils.validateToken(jwt.get())) {
			return Mono.empty();
		}

		Optional<String> username = authJwtUtils.getUsername(jwt.get());
		if (username.isEmpty()) {
			return Mono.empty();
		}
		
		//username代表驗證通過的使用者帳號
		return Mono.just(new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>()));
	}

}
