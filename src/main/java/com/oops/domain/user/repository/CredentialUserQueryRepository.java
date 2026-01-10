package com.oops.domain.user.repository;

import com.oops.domain.user.model.CredentialUser;
import com.oops.outbound.mysql.user.repository.CredentialUserJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CredentialUserQueryRepository {

    private final CredentialUserJpaRepository credentialUserJpaRepository;

    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        return credentialUserJpaRepository.existsByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<CredentialUser> findByUsernameAndEncPassword(String username, String passwordHash) {
        return credentialUserJpaRepository.findByUsernameAndPasswordHash(username, passwordHash);
    }
}
