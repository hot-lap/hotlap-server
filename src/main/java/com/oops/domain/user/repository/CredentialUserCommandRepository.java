package com.oops.domain.user.repository;

import com.oops.domain.user.model.CredentialUser;
import com.oops.outbound.mysql.user.repository.CredentialUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CredentialUserCommandRepository {

    private final CredentialUserJpaRepository credentialUserJpaRepository;

    @Transactional
    public CredentialUser save(CredentialUser credentialUser) {
        return credentialUserJpaRepository.save(credentialUser);
    }
}
