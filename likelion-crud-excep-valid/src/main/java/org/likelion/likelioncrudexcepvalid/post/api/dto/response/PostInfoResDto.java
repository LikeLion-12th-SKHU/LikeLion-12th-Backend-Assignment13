package org.likelion.likelioncrudexcepvalid.post.api.dto.response;

import lombok.Builder;
import org.likelion.likelioncrudexcepvalid.post.domain.Post;

@Builder
public record PostInfoResDto(
        String title,
        String contents,
        String writer
) {
    public static PostInfoResDto from(Post post) {
        return PostInfoResDto.builder()
                .title(post.getTitle())
                .contents(post.getContents())
                .writer(post.getMember().getName())
                .build();
    }
}