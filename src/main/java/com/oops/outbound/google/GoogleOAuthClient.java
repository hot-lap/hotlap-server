package com.oops.outbound.google;

import com.oops.outbound.google.model.GoogleUserInfoResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "https://www.googleapis.com", accept = MediaType.APPLICATION_JSON_VALUE)
public interface GoogleOAuthClient {

    @GetExchange("/oauth2/v3/userinfo")
    GoogleUserInfoResponse getUserInfo(@RequestHeader("Authorization") String bearerToken);
}
