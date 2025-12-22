package com.oops.domain.post.repository;

import com.oops.common.exception.ErrorCode;
import com.oops.common.exception.NotFoundException;
import com.oops.domain.post.model.Post;
import com.oops.outbound.mysql.post.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class PostCommandRepository {

    private final PostJpaRepository postJpaRepository;

    @Transactional
    public Post save(Post post) {
        return postJpaRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Post findByIdOrThrow(Long postId) {
        return postJpaRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_POST_ERROR));
    }

    @Transactional
    public void deleteById(Long postId) {
        postJpaRepository.deleteById(postId);
    }

}
