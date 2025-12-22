package com.oops.application.post;

import com.oops.application.post.model.command.PostCreateCommand;
import com.oops.application.post.model.command.PostUpdateCommand;
import com.oops.application.post.model.result.PostResult;
import com.oops.domain.post.model.Post;
import com.oops.domain.post.model.vo.ImpactIntensity;
import com.oops.domain.post.repository.PostCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostCommandService {
    private final PostCommandRepository postCommandRepository;

    @Transactional
    public PostResult create(PostCreateCommand command) {
        var createdPost = postCommandRepository.save(
                Post.builder()
                        .content(command.content())
                        .impactIntensity(new ImpactIntensity(command.impactIntensity()))
                        .category(command.category())
                        .cause(command.cause())
                        .feeling(command.feeling())
                        .build()
        );
        return PostResult.from(createdPost);
    }

    @Transactional(readOnly = true)
    public PostResult read(Long postId) {
        var post = postCommandRepository.findByIdOrThrow(postId);
        return PostResult.from(post);
    }

    @Transactional
    public PostResult update(Long postId, PostUpdateCommand command) {
        Post findPost = postCommandRepository.findByIdOrThrow(postId);

        findPost.update(command.content(), new ImpactIntensity(command.impactIntensity()),
                command.category(), command.cause(), command.feeling());

        return PostResult.from(findPost);
    }

    @Transactional
    public String delete(Long postId) {
        postCommandRepository.deleteById(postId);
        return "post 가 성공적으로 삭제되었습니다.";
    }

}
