package org.likelion.likelioncrudexcepvalid.common.Exception;

import lombok.Getter;
import org.likelion.likelioncrudexcepvalid.common.error.ErrorCode;

@Getter
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;
    // ErrorCode 변수 선언

    public CustomException(ErrorCode error, String message) {
        super(message);
        this.errorCode = error;
    }
    // ErrorCode 객체와 예외 메시지를 전달받아 초기화
    // super(message) 호출을 통해 RuntimeException의 생성자를 호출하여 예외 메시지를 설정합니다.
    // this.errorCode = error;를 통해 errorCode 필드에 전달받은 ErrorCode 객체를 할당합니다.

    public int getHttpStatus() {
        return errorCode.getHttpStatusCode();
    }
}

