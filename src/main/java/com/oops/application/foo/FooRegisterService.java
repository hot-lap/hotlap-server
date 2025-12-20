package com.oops.application.foo;

import com.oops.application.foo.model.command.FooRegisterCommand;
import com.oops.application.foo.model.result.FooResult;
import com.oops.domain.foo.model.Foo;
import com.oops.domain.foo.repository.FooCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FooRegisterService {

	private final FooCommandRepository fooCommandRepository;

	public FooResult register(FooRegisterCommand command) {
		var createdFoo = fooCommandRepository.save(Foo.builder().description(command.description()).build());

		return FooResult.from(createdFoo);
	}

}
