package org.likelion.likelioncrudexcepvalid.post.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostSaveReqDto(
        @NotNull(message = "작성자를 필수로 입력해야 합니다.")
        Long memberId,

        @NotBlank(message = "제목을 필수로 입력해야 합니다.")
        String title,
        @NotBlank(message = "내용을 필수로 입력해야 합니다.")
        String contents
) {
}