package com.oops.outbound.google;

import com.oops.outbound.google.model.GoogleOAuthTokenResponse;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange(
        url = "https://oauth2.googleapis.com",
        accept = MediaType.APPLICATION_JSON_VALUE,
        contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
public interface GoogleOAuthTokenClient {

    @PostExchange("/token")
    GoogleOAuthTokenResponse getToken(@RequestBody MultiValueMap<String, String> formData);
}
