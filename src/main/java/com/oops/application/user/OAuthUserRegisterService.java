package com.oops.application.user;

import com.oops.application.user.model.command.OAuthUserRegisterCommand;
import com.oops.application.user.model.result.OAuthUserResult;
import com.oops.domain.user.model.OAuthUser;
import com.oops.domain.user.repository.OAuthUserCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthUserRegisterService {

	private final OAuthUserCommandRepository oAuthUserCommandRepository;

	public OAuthUserResult register(OAuthUserRegisterCommand command) {
		var oauthUser = OAuthUser.builder()
			.provider(command.provider())
			.oauthId(command.oauthId())
			.userId(command.userId())
			.build();

		var saved = oAuthUserCommandRepository.save(oauthUser);
		return OAuthUserResult.from(saved);
	}

}
