package com.oops.application.user;

import com.oops.application.user.model.result.UserResult;
import com.oops.domain.user.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInquiryService {

	private final UserQueryRepository userQueryRepository;

	public UserResult findById(Long id) {
		var user = userQueryRepository.findByIdOrThrow(id);
		return UserResult.from(user);
	}

}
