package com.oops.domain.user.repository;

import com.oops.common.exception.ErrorCode;
import com.oops.common.exception.InvalidRequestException;
import com.oops.domain.user.model.User;
import com.oops.outbound.mysql.user.repository.UserJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryRepository {

    private final UserJpaRepository userJpaRepository;

    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id);
    }

    public User findByIdOrThrow(Long id) {
        return userJpaRepository
                .findById(id)
                .orElseThrow(() -> new InvalidRequestException(ErrorCode.NOT_FOUND_USER_ERROR));
    }
}
