package org.likelion.likelioncrudexcepvalid.common.Exception;

import org.likelion.likelioncrudexcepvalid.common.error.ErrorCode;

public class NotFoundException extends CustomException {
    public NotFoundException(ErrorCode errorCode, String message) {
        // super(message); :: RuntimeException의 생성자를 호출해 예외 메세지를 설정합니다
        // this.errorCode = error; :: errorCode 필드에 ErrorCode 객체을 할당합니다
        super(errorCode, message);
    }
}
