package com.oops.application.user.model.result;

import com.oops.domain.user.model.CredentialUser;

import java.time.LocalDateTime;

public record CredentialUserResult(Long id, Long uid, String username, LocalDateTime createdAt,
		LocalDateTime modifiedAt) {
	public static CredentialUserResult from(CredentialUser credentialUser) {
		return new CredentialUserResult(credentialUser.getId(), credentialUser.getUid(),
				credentialUser.getUsername(), credentialUser.getCreatedAt(), credentialUser.getModifiedAt());
	}
}
