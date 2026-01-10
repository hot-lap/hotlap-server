package com.oops.outbound.kakao;

import com.oops.outbound.kakao.model.KakaoOAuthTokenResponse;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange(
		url = "https://kauth.kakao.com",
		accept = MediaType.APPLICATION_JSON_VALUE,
		contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE
)
public interface KakaoOAuthTokenClient {

	@PostExchange("/oauth/token")
	KakaoOAuthTokenResponse getToken(@RequestBody MultiValueMap<String, String> formData);
}
