package com.example.apigateway.config;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {
    @Autowired
    private RouterValidator routerValidator;
    @Autowired
    private JwtUtils jwtUtils;

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest serverHttpRequest) {
        return serverHttpRequest.getHeaders().getOrEmpty("Authorization").get(0);
    }

    private boolean isAuthMissing(ServerHttpRequest serverHttpRequest) {
        return !serverHttpRequest.getHeaders().containsKey("Authorization");
    }

    private void populateRequestWithHeader(ServerWebExchange exchange, String token) {
        Claims claims = jwtUtils.getAllClaimsFromToken(token);
        exchange.getRequest().mutate()
                .header("id", String.valueOf(claims.get("id")))
                .header("name", String.valueOf(claims.get("name")))
                .build();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (routerValidator.isSecured.test(request)) {
            if (this.isAuthMissing(request)) {
                return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);
            }

            final String token = this.getAuthHeader(request);

            if (jwtUtils.isInvalid(token)) {
                return this.onError(exchange, "Authorization header id Invalid", HttpStatus.UNAUTHORIZED);
            }
            this.populateRequestWithHeader(exchange, token);
        }
        return chain.filter(exchange);
    }

}
