package com.example.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Autowired
    AuthenticationFilter filter;
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/user/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://user-service")
                )
                .route("auth-service", r->r.path("/auth/**")
                        .filters(f->f.filter(filter))
                        .uri("lb://auth-service")
                )
                .route("message-service", r->r.path("/message/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://message-service")
                )
                .route("save-message", r->r.path("/save/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://save-message")
                )
                .build();
    }
}
