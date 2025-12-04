package com.hotlap.server.inbound.controller.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
public class PageResponse<T> {
    private List<T> content;
    private int page;
    private int size;
    private int totalPage;
    private long totalCount;

    public static <T> ResponseDto<PageResponse<T>> wrap(Page<T> data) {
        var pageData = new PageResponse<T>(
                data.getContent(),
                data.getPageable().getPageNumber(),
                data.getSize(),
                data.getTotalPages(),
                data.getTotalElements()
        );

        return ResponseDto.wrap(pageData);
    }
}
