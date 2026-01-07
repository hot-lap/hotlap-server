package com.oops.application.user;

import com.oops.application.user.model.command.UserRegisterCommand;
import com.oops.application.user.model.result.UserResult;
import com.oops.common.encrypt.EncryptData;
import com.oops.domain.user.model.User;
import com.oops.domain.user.repository.UserCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegisterService {

    private final UserCommandRepository userCommandRepository;

    public UserResult register(UserRegisterCommand command) {
        var user = User.builder()
                .email(new EncryptData(command.email()))
                .name(command.name())
                .build();

        var savedUser = userCommandRepository.save(user);
        return UserResult.from(savedUser);
    }
}
