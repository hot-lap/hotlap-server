package com.hotlap.server.domain.foo.repository;

import com.hotlap.server.common.exception.ErrorCode;
import com.hotlap.server.common.exception.NotFoundException;
import com.hotlap.server.domain.foo.model.Foo;
import com.hotlap.server.outbound.mysql.foo.repository.FooJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class FooQueryRepository {
    private final FooJpaRepository fooJpaRepository;

    @Transactional(readOnly = true)
    public Foo findByIdOrThrow(Long id) {
        return fooJpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_FOO_ERROR));
    }
}
