package com.oops.config.auth;

import com.oops.outbound.google.GoogleOAuthClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class GoogleOAuthConfig {

    @Bean
    public RestClient googleRestClient(RestClient.Builder builder) {
        return builder.baseUrl("https://www.googleapis.com")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public GoogleOAuthClient googleOAuthClient(RestClient googleRestClient) {
        return HttpServiceProxyFactory.builderFor(RestClientAdapter.create(googleRestClient))
                .build()
                .createClient(GoogleOAuthClient.class);
    }
}
