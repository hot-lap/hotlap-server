package com.oops.domain.auth.model;

public interface AuthUser {

    Long getUid();

    AuthContext getContext();

    boolean isAuthor(Long uid);

    void isAuthorThrow(Long uid);

    void isNotAuthorThrow(Long uid);
}
