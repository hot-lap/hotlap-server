package com.oops.outbound.mysql.auth.repository;

import com.oops.domain.auth.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenJpaRepository extends JpaRepository<RefreshToken, Long> {

	void deleteByRefreshToken(String refreshToken);

}
