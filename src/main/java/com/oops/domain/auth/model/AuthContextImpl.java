package com.oops.domain.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthContextImpl implements AuthContext {

    private final String name;

    @Override
    public String name() {
        return name;
    }
}
