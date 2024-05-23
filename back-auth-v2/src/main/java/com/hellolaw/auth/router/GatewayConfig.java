package com.hellolaw.auth.router;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;

@Configuration
public class GatewayConfig {
	@Value("${gateway.router.api-url}")
	private String apiURL;

	@Bean
	public RouteLocator RouterLocator(RouteLocatorBuilder builder) {
		return builder.routes()
			.route("test_route", r -> r.path("/test/**")
				.filters(f -> f
					.rewritePath("/test(?<segment>/?.*)", "/api/test${segment}")
					.preserveHostHeader()
					.filters(authenticationFilter())
				)
				.uri(apiURL))
			.route("answer_route", r -> r.path("/answer/**")
				.filters(f -> f
					.rewritePath("/answer(?<segment>/?.*)", "/api/answer${segment}")
					.preserveHostHeader()
					.filters(authenticationFilter())
				)
				.uri(apiURL))
			.route("healthcheck_route", r -> r.path("/healthcheck/**")
				.filters(f -> f
					.rewritePath("/healthcheck(?<segment>/?.*)", "/api/healthcheck${segment}")
					.preserveHostHeader()
					.filters(authenticationFilter())
				)
				.uri(apiURL))
			.route("law_route", r -> r.path("/law/**")
				.filters(f -> f
					.rewritePath("/law(?<segment>/?.*)", "/api/law${segment}")
					.preserveHostHeader()
					.filters(authenticationFilter())
				)
				.uri(apiURL))
			.route("precedent_route", r -> r.path("/precedent/**")
				.filters(f -> f
					.rewritePath("/precedent(?<segment>/?.*)", "/api/precedent${segment}")
					.preserveHostHeader()
					.filters(authenticationFilter())
				)
				.uri(apiURL))
			.route("question_route", r -> r.path("/question/**")
				.filters(f -> f
					.rewritePath("/question(?<segment>/?.*)", "/api/question${segment}")
					.preserveHostHeader()
					.filters(authenticationFilter())
				)
				.uri(apiURL))
			.build();
	}

	private GatewayFilter authenticationFilter() {
		return (exchange, chain) -> ReactiveSecurityContextHolder.getContext()
			.map(securityContext -> securityContext.getAuthentication())
			.flatMap(authentication -> {
				if (authentication != null && authentication.isAuthenticated()) {
					String id = String.valueOf(authentication.getPrincipal());
					exchange.getRequest().mutate()
						.header("authorization", id)
						.build();
				}
				return chain.filter(exchange);
			});
	}
}
