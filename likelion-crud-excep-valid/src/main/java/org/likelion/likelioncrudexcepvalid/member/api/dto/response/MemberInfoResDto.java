package org.likelion.likelioncrudexcepvalid.member.api.dto.response;

import lombok.Builder;
import org.likelion.likelioncrudexcepvalid.member.domain.Member;
import org.likelion.likelioncrudexcepvalid.member.domain.Part;

@Builder
public record MemberInfoResDto(
        String name,
        int age,
        String email
//        Part part
) {
    public static MemberInfoResDto from(Member member) {
        return MemberInfoResDto.builder()
                .name(member.getName())
                .age(member.getAge())
                .email(member.getEmail())
//                .part(member.getPart())
                .build();
    }
}