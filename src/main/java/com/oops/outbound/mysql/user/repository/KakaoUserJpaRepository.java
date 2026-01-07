package com.oops.outbound.mysql.user.repository;

import com.oops.domain.user.model.KakaoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KakaoUserJpaRepository extends JpaRepository<KakaoUser, Long> {}
