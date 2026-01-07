package com.oops.domain.auth.model;

import com.oops.common.exception.ErrorCode;
import com.oops.common.exception.NoAuthorityException;
import com.oops.domain.user.model.User;
import lombok.Getter;

@Getter
public class AuthUserImpl implements AuthUser {

    private final Long uid;

    private final AuthContext context;

    public AuthUserImpl(Long uid, AuthContext context) {
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

    public static AuthUserImpl from(User user) {
        return new AuthUserImpl(user.getId(), new AuthContextImpl(user.getNickname()));
    }

    public static AuthUserImpl fromAnon() {
        return new AuthUserImpl(-1L, new AuthContextImpl("ANON-SYSTEM"));
    }
}
