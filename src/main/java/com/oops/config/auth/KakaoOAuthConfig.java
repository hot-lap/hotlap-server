package com.oops.config.auth;

import com.oops.outbound.kakao.KakaoOAuthClient;
import com.oops.outbound.kakao.KakaoOAuthTokenClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class KakaoOAuthConfig {

    @Bean
    public RestClient kakaoRestClient(RestClient.Builder builder) {
        return builder.baseUrl("https://kapi.kakao.com")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public KakaoOAuthClient kakaoOAuthClient(RestClient kakaoRestClient) {
        return HttpServiceProxyFactory.builderFor(RestClientAdapter.create(kakaoRestClient))
                .build()
                .createClient(KakaoOAuthClient.class);
    }

    @Bean
    public KakaoOAuthTokenClient kakaoOAuthTokenClient(RestClient kakaoRestClient) {
        return HttpServiceProxyFactory.builderFor(RestClientAdapter.create(kakaoRestClient))
                .build()
                .createClient(KakaoOAuthTokenClient.class);
    }
}
