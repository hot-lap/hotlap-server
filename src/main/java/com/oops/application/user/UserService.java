package com.oops.application.user;

import com.oops.application.auth.model.result.UserResult;
import com.oops.domain.user.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserQueryRepository userQueryRepository;

    public UserResult getUser(Long uid) {
        var user = userQueryRepository.findByIdOrThrow(uid);

        return new UserResult(user.getId(), user.getName(), user.getNickname());
    }
}
