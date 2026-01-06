package com.oops.inbound.controller.oauth;

import com.oops.application.auth.OAuthFacade;
import com.oops.inbound.controller.common.model.ResponseDto;
import com.oops.inbound.controller.oauth.model.request.OAuthSignUpRequest;
import com.oops.inbound.controller.oauth.model.response.AuthResponse;
import com.oops.inbound.controller.oauth.model.response.OAuthCheckResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/oauth")
@RequiredArgsConstructor
@Tag(name = "OAuth", description = "소셜 로그인 API")
public class OAuthController {

	private final OAuthFacade oAuthFacade;

	@PostMapping("/{provider}/signup")
	@Operation(summary = "OAuth 회원 가입")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDto<AuthResponse> signup(@PathVariable String provider,
			@RequestBody @Valid OAuthSignUpRequest request) {
		var response = oAuthFacade.signUp(request.toCommand(provider));
		return ResponseDto.wrap(AuthResponse.from(response));
	}

	@GetMapping("/{provider}/signup/check")
	@Operation(summary = "가입 여부 확인")
	public ResponseDto<OAuthCheckResponse> checkSignUp(@PathVariable String provider,
			@RequestParam String accessToken) {
		var response = oAuthFacade.checkSignUp(provider, accessToken);
		return ResponseDto.wrap(OAuthCheckResponse.from(response));
	}

}
