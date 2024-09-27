package org.likelion.likelioncrudexcepvalid.member.api.dto.request;

import jakarta.validation.constraints.*;
import org.likelion.likelioncrudexcepvalid.member.application.Validator.EnumValid;
import org.likelion.likelioncrudexcepvalid.member.domain.Part;

public record MemberSaveReqDto(

        @NotBlank(message = "이름을 필수로 입력해야 합니다.")
        @Size(min = 2, max = 15, message = "2자 이상, 10자 이하로 입력하세요.")
        String name,

        @NotNull(message = "나이를 필수로 입력하시오.")
        @Positive(message = "연나이를 입력해 주세요")
        @Max(value = 100, message = "1부터 100사기의 값만 입력할 수 있습니다.")
        int age,

        @NotBlank(message = "이메일을 필수로 입력해야 합니다.")
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 맞지 않습니다.")
        String email,

        @EnumValid(enumClass = Part.class)
        Part part
) {
}