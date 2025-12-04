package com.hotlap.server.domain.foo.repository;

import com.hotlap.server.domain.foo.model.Foo;
import com.hotlap.server.outbound.mysql.foo.repository.FooJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FooCommandRepository {
    private final FooJpaRepository fooJpaRepository;

    @Transactional
    public Foo save(Foo foo) {
        return fooJpaRepository.save(foo);
    }
}
