package com.oops.outbound.mysql.user.repository;

import com.oops.domain.user.model.NaverUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NaverUserJpaRepository extends JpaRepository<NaverUser, Long> {}
