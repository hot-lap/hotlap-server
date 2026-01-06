package com.oops.application.user;

import com.oops.application.user.model.command.CredentialUserRegisterCommand;
import com.oops.application.user.model.result.CredentialUserResult;
import com.oops.domain.user.model.CredentialUser;
import com.oops.domain.user.repository.CredentialUserCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CredentialUserRegisterService {

	private final CredentialUserCommandRepository credentialUserCommandRepository;

	private final PasswordEncoder passwordEncoder;

	public CredentialUserResult register(CredentialUserRegisterCommand command) {
		var credentialUser = CredentialUser.builder()
			.uid(command.uid())
			.username(command.username())
			.passwordHash(passwordEncoder.encode(command.password()))
			.build();

		var saved = credentialUserCommandRepository.save(credentialUser);
		return CredentialUserResult.from(saved);
	}

}
