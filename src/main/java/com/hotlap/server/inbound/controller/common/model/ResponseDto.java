package com.hotlap.server.inbound.controller.common.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseDto<T> {
    private T data;

    public static <T> ResponseDto<T> wrap(T data) {
        return new ResponseDto<T>(data);
    }
}
