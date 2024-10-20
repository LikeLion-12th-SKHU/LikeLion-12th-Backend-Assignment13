package org.likelion.likelioncrudexcepvalid.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    //200 ok
    GET_SUCCESS(HttpStatus.OK, "성공적인 조회"),
    POST_UPDATE_SUCCESS(HttpStatus.OK, "글이 성공적으로 수정됨"),
    MEMBER_UPDATE_SUCCESS(HttpStatus.OK, "사용자가 성공적으로 수정됨"),
    POST_DELETE_SUCCESS(HttpStatus.OK, "글이 성공적으로 조회됨"),
    MEMBER_DELETE_SUCCESS(HttpStatus.OK, "사용자 데이터가 성공적으로 삭제됨"),

    POST_SAVE_SUCCESS(HttpStatus.CREATED, "글이 성공적으로 저장됨"),
    MEMBER_SAVE_SUCCESS(HttpStatus.CREATED, "사용자가 성공적으로 저장됨");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode(){
        return httpStatus.value();
    }
}