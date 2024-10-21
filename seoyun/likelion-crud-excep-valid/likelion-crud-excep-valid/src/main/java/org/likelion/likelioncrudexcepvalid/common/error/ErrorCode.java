package org.likelion.likelioncrudexcepvalid.common.error;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {
    /**
     * 404 NOT FOUND
     */
    POSTS_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 게시물이 없습니다. postId=", "NOT_FOUND_404"),
    MEMBERS_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 회원이 없습니다. memberId=", "NOT_FOUND_404"),

    /**
     * 500 INTERNAL SERVER ERROR
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생했습니다.", "INTERNAL_SERVER_ERROR"),

    /**
     * 400 BAD REQUEST
     */
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "유효성 검사에 맞지 않습니다.", "BAD_REQUEST_400");

    private final HttpStatus httpStatus;
    private final String message;
    private final String code;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
