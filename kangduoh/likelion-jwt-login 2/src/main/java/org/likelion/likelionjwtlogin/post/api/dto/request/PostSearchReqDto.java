package org.likelion.likelionjwtlogin.post.api.dto.request;

public record PostSearchReqDto(
        String titleKeyword,
        String contentKeyword
) {
}
