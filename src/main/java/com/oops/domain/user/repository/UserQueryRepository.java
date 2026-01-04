package com.oops.domain.user.repository;

import com.oops.common.exception.ErrorCode;
import com.oops.common.exception.NotFoundException;
import com.oops.domain.user.model.User;
import com.oops.outbound.mysql.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {

	private final UserJpaRepository userJpaRepository;

	@Transactional(readOnly = true)
	public User findByIdOrThrow(Long id) {
		return findById(id).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_FOO_ERROR));
	}

	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) {
		return userJpaRepository.findById(id);
	}

}
