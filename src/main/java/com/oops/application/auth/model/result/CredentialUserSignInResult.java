package com.oops.application.auth.model.result;

import com.oops.domain.auth.model.TokenContext;

public record CredentialUserSignInResult(TokenContext context, Long uid) {}
