package com.hotlap.server.inbound.controller.foo;

import com.hotlap.server.application.foo.FooInquiryService;
import com.hotlap.server.application.foo.FooRegisterService;
import com.hotlap.server.inbound.controller.common.model.ResponseDto;
import com.hotlap.server.inbound.controller.foo.model.request.FooRegisterRequest;
import com.hotlap.server.inbound.controller.foo.model.response.FooResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Tag(name = "foo")
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class FooController {
    private final FooRegisterService fooRegisterService;
    private final FooInquiryService fooInquiryService;

    @Operation(summary = "foo를 등록하라!")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/foo")
    public ResponseDto<FooResponse> register(@RequestBody FooRegisterRequest request) {
        var result = fooRegisterService.register(request.toCommand());
        return ResponseDto.wrap(FooResponse.from(result));
    }

    @Operation(summary = "foo를 조회하라!")
    @GetMapping("/api/v1/foo/{id}")
    public ResponseDto<FooResponse> inquiry(@PathVariable Long id) {
        var result = fooInquiryService.inquiry(id);
        return ResponseDto.wrap(FooResponse.from(result));
    }
}
