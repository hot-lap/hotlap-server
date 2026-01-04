package com.oops.domain.user.repository;

import com.oops.outbound.mysql.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserCommandRepository {

	private final UserJpaRepository userJpaRepository;

}
