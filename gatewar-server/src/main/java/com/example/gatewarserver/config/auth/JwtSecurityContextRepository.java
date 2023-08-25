package com.example.gatewarserver.config.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.server.ServerWebExchange;

import com.example.gatewarserver.utils.AuthJwtUtils;
import com.google.common.base.Optional;

import reactor.core.publisher.Mono;

/**
 * <p>負責管理 SecurityContext，用於存儲已驗證身份的信息，包括 Authentication 物件、使用者權限等。</p>
 * @author k9798909
 *
 */
public class JwtSecurityContextRepository implements ServerSecurityContextRepository {
	
	private AuthJwtUtils jwtUtils;
	private JwtAuthenticationManager jwtAuthenticationManager;

	public JwtSecurityContextRepository(AuthJwtUtils jwtUtils,JwtAuthenticationManager jwtAuthenticationManager) {
		super();
		this.jwtUtils = jwtUtils;
		this.jwtAuthenticationManager = jwtAuthenticationManager;
	}
	
	
	@Override
	public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
		return Mono.empty();
	}

	@Override
	public Mono<SecurityContext> load(ServerWebExchange exchange) {
        Optional<String> token = Optional.fromNullable(
				exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION)
        );
        
        if (!token.isPresent()) {
        	return Mono.empty();
        }
        
        if (!token.get().startsWith("Bearer ")) {
        	return Mono.empty();
        }
        
        String jwt = token.get().substring(7);
        
        if (!jwtUtils.verify(jwt)) {
        	return Mono.empty();
        }
        
    	//第一個參數通過的jwt會再給JwtAuthenticationManager驗證，第二個jwt因token驗證無密碼故將jwt傳入
        Authentication auth = new UsernamePasswordAuthenticationToken(jwt, jwt);
        return jwtAuthenticationManager.authenticate(auth).map(SecurityContextImpl::new);
	}

}
