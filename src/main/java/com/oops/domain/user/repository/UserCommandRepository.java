package com.oops.domain.user.repository;

import com.oops.domain.user.model.User;
import com.oops.outbound.mysql.user.repository.UserJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserCommandRepository {

    private final UserJpaRepository userJpaRepository;

    @Transactional
    public User save(User user) {
        return userJpaRepository.save(user);
    }
}
