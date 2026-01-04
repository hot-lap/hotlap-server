package com.oops.domain.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface AuthContext {

	String name();

	@Getter
	@AllArgsConstructor
	class Default implements AuthContext {

		private final String name;

		@Override
		public String name() {
			return name;
		}

	}

}
