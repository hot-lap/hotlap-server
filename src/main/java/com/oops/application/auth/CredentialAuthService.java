package com.oops.application.auth;

import com.oops.application.auth.model.command.CredentialAuthRegisterCommand;
import com.oops.application.auth.model.command.CredentialUserSignInCommand;
import com.oops.application.auth.model.result.CredentialUserSignInResult;
import com.oops.application.user.CredentialUserRegisterService;
import com.oops.application.user.model.command.CredentialUserRegisterCommand;
import com.oops.common.exception.AlreadyExistsException;
import com.oops.common.exception.ErrorCode;
import com.oops.common.exception.NotFoundException;
import com.oops.domain.user.model.User;
import com.oops.domain.user.repository.CredentialUserQueryRepository;
import com.oops.domain.user.repository.UserCommandRepository;
import com.oops.domain.user.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@RequiredArgsConstructor
public class CredentialAuthService {

    private final CredentialUserQueryRepository credentialUserQueryRepository;

    private final UserCommandRepository userCommandRepository;

    private final UserQueryRepository userQueryRepository;

    private final TransactionTemplate transactionTemplate;

    private final PasswordEncoder passwordEncoder;
    private final CredentialUserRegisterService credentialUserRegisterService;

    private final JwtTokenService jwtTokenService;

    public void createCredentialUser(CredentialAuthRegisterCommand command) {
        if (credentialUserQueryRepository.existsByUsername(command.username())) {
            throw new AlreadyExistsException(ErrorCode.ALREADY_EXISTS_CREDENTIAL_USERNAME_ERROR);
        }

        transactionTemplate.execute(_ -> {
            var createdUser = userCommandRepository.save(User.builder()
                    .name(command.name())
                    .nickname(command.nickname())
                    .build());

            credentialUserRegisterService.register(
                    new CredentialUserRegisterCommand(createdUser.getId(), command.username(), command.password()));

            return createdUser;
        });
    }

    public CredentialUserSignInResult signIn(CredentialUserSignInCommand command) {
        var credentialUser = credentialUserQueryRepository
                .findByUsernameAndEncPassword(command.username(), passwordEncoder.encode(command.password()))
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER_ERROR));

        var user = userQueryRepository.findByIdOrThrow(credentialUser.getUid());

        var token = jwtTokenService.generateAccessAndRefreshToken(user.getId());

        return new CredentialUserSignInResult(token, user.getId());
    }
}
