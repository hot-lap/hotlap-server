package com.oops.application.foo;

import com.oops.application.foo.model.result.FooResult;
import com.oops.domain.foo.repository.FooQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface FooInquiryService {

    FooResult inquiry(Long id);

    @Service
    @RequiredArgsConstructor
    class Default implements FooInquiryService {

        private final FooQueryRepository fooQueryRepository;

        @Override
        public FooResult inquiry(Long id) {
            var foo = fooQueryRepository.findByIdOrThrow(id);
            return FooResult.from(foo);
        }
    }
}
