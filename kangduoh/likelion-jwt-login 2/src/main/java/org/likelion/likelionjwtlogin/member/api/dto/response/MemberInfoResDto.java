package org.likelion.likelionjwtlogin.member.api.dto.response;

import lombok.Builder;
import org.likelion.likelionjwtlogin.member.domain.Member;

@Builder
public record MemberInfoResDto(
        String email,
        String nickname
) {
    public static MemberInfoResDto from(Member member) {
        return MemberInfoResDto.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }
}
