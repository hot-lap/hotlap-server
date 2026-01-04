package com.oops.domain.auth.model;

import com.oops.common.exception.ErrorCode;
import com.oops.common.exception.NoAuthorityException;
import com.oops.domain.user.model.User;
import lombok.Getter;

public interface AuthUser {

	Long getUid();

	AuthContext getContext();

	boolean isAuthor(Long uid);

	void isAuthorThrow(Long uid);

	void isNotAuthorThrow(Long uid);

	@Getter
	class Default implements AuthUser {

		private final Long uid;

		private final AuthContext context;

		public Default(Long uid, AuthContext context) {
			this.uid = uid;
			this.context = context;
		}

		@Override
		public boolean isAuthor(Long uid) {
			return this.uid.equals(uid);
		}

		@Override
		public void isAuthorThrow(Long uid) {
			if (isAuthor(uid)) {
				throw new NoAuthorityException(ErrorCode.NO_AUTHORITY_ERROR);
			}
		}

		@Override
		public void isNotAuthorThrow(Long uid) {
			if (!isAuthor(uid)) {
				throw new NoAuthorityException(ErrorCode.NO_AUTHORITY_ERROR);
			}
		}

		public static Default from(User user) {
			return new Default(user.getId(), new AuthContext.Default(user.getNickname()));
		}

		public static Default fromAnon() {
			return new Default(-1L, new AuthContext.Default("INTERNAL-SYSTEM"));
		}

	}

}
