package com.oops.inbound.controller.post;

import com.oops.application.post.PostCommandService;
import com.oops.inbound.controller.common.model.ResponseDto;
import com.oops.inbound.controller.post.model.request.PostCreateRequest;
import com.oops.inbound.controller.post.model.request.PostUpdateRequest;
import com.oops.inbound.controller.post.model.response.PostResponse;
import com.oops.inbound.controller.post.model.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "01. 게시글")
@RestController
@RequestMapping(value = "/api/v1/posts", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PostController {

    private final PostCommandService postCommandService;

    @Operation(summary = "게시글을 생성한다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseDto<PostResponse> create(
            @Valid @RequestBody PostCreateRequest request) {
        var result = postCommandService.create(request.toCommand());
        return ResponseDto.wrap(PostResponse.from(result));
    }

    @Operation(summary = "게시글을 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{postId}")
    public ResponseDto<PostResponse> read(
            @Parameter(description = "게시글 고유 번호")
            @PathVariable("postId") Long postId
    ) {
        var result = postCommandService.read(postId);
        return ResponseDto.wrap(PostResponse.from(result));
    }

    @Operation(summary = "게시글을 수정한다.")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{postId}")
    public ResponseDto<PostResponse> update(
            @Parameter(description = "게시글 고유 번호")
            @PathVariable("postId") Long postId,
            @Valid @RequestBody PostUpdateRequest request) {
        var result = postCommandService.update(postId, request.toCommand());
        return ResponseDto.wrap(PostResponse.from(result));
    }

    @Operation(summary = "게시글을 삭제한다.")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{postId}")
    public ResponseDto<SuccessResponse> delete(
            @Parameter(description = "게시글 고유 번호")
            @PathVariable("postId") Long postId) {
        var result = postCommandService.delete(postId);
        return ResponseDto.wrap(SuccessResponse.from(result));
    }
}
