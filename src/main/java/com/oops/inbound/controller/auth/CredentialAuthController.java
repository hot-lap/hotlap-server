package com.oops.inbound.controller.auth;

import com.oops.application.auth.CredentialAuthService;
import com.oops.inbound.controller.auth.model.request.CredentialUserSignInRequest;
import com.oops.inbound.controller.auth.model.request.CredentialUserSignUpRequest;
import com.oops.inbound.controller.auth.model.response.CredentialUserSignInResponse;
import com.oops.inbound.controller.common.model.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CredentialAuthController {

    private final CredentialAuthService credentialAuthService;

    @Operation(summary = "일반 회원가입")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/api/v1/auth/credential-users/sign-up")
    public void signup(CredentialUserSignUpRequest request) {
        var command = request.toCommand();
        credentialAuthService.createCredentialUser(command);
    }

    @Operation(summary = "일반 유저 로그인")
    @PostMapping("/api/v1/auth/credential-users/sign-in")
    public ResponseDto<CredentialUserSignInResponse> signIn(CredentialUserSignInRequest request) {
        var command = request.toCommand();
        var result = credentialAuthService.signIn(command);
        return ResponseDto.wrap(CredentialUserSignInResponse.of(result));
    }
}
