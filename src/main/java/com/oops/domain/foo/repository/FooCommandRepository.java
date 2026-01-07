package com.oops.domain.foo.repository;

import com.oops.domain.foo.model.Foo;
import com.oops.outbound.mysql.foo.repository.FooJpaRepository;
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
