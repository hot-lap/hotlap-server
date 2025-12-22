package com.oops.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("예기치 못한 에러가 발생했습니다."),
    INVALID_REQUEST_ERROR("잘못된 요청입니다."),

    NOT_FOUND_FOO_ERROR("foo를 찾을 수 없습니다."),
    NOT_FOUND_POST_ERROR("post를 찾을 수 없습니다")
    ;

    private final String message;
}
