package com.oops.inbound.controller.user;

import com.oops.application.user.UserService;
import com.oops.domain.auth.model.AuthUser;
import com.oops.inbound.controller.common.model.ResponseDto;
import com.oops.inbound.controller.user.model.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "01. 유저")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "토큰으로 사용자 정보 조회")
    @GetMapping("/api/v1/my-info")
    public ResponseDto<UserResponse> getMyInfo(AuthUser user) {
        var result = userService.getUser(user.getUid());
        return ResponseDto.wrap(UserResponse.from(result));
    }
}
