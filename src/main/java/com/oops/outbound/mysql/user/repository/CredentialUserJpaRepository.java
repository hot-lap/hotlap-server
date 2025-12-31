package com.oops.outbound.mysql.user.repository;

import com.oops.domain.user.model.CredentialUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialUserJpaRepository extends JpaRepository<CredentialUser, Long> {

}
