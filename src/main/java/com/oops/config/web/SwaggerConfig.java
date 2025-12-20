package com.oops.config.web;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

	@Bean
	public GroupedOpenApi api() {
		return GroupedOpenApi.builder().group("api").pathsToMatch("/api/**").build();
	}

	@Bean
	public GroupedOpenApi dev() {
		return GroupedOpenApi.builder().group("dev").pathsToMatch("/dev/**").build();
	}

}
