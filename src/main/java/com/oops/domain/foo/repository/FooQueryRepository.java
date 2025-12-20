package com.oops.domain.foo.repository;

import com.oops.common.exception.ErrorCode;
import com.oops.common.exception.NotFoundException;
import com.oops.domain.foo.model.Foo;
import com.oops.outbound.mysql.foo.repository.FooJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class FooQueryRepository {

	private final FooJpaRepository fooJpaRepository;

	@Transactional(readOnly = true)
	public Foo findByIdOrThrow(Long id) {
		return fooJpaRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_FOO_ERROR));
	}

}
