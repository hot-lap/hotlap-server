package com.oops.outbound.mysql.user.repository;

import com.oops.domain.user.model.GoogleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleUserJpaRepository extends JpaRepository<GoogleUser, Long> {

}
