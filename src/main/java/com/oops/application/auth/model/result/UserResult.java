package com.oops.application.auth.model.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResult {

    private Long id;

    private String name;

    private String nickname;
}
