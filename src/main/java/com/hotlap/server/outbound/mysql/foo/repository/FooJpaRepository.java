package com.hotlap.server.outbound.mysql.foo.repository;

import com.hotlap.server.domain.foo.model.Foo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooJpaRepository extends JpaRepository<Foo, Long> {
}
