package com.oops.outbound.kakao;

import com.oops.outbound.kakao.model.KakaoUserInfoResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange(url = "https://kapi.kakao.com", accept = MediaType.APPLICATION_JSON_VALUE)
public interface KakaoOAuthClient {

    @PostExchange("/v2/user/me")
    KakaoUserInfoResponse getUserInfo(@RequestHeader("Authorization") String bearerToken);
}
