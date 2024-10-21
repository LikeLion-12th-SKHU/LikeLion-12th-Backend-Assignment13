package org.likelion.likelioncrudexcepvalid.common.exception;

import lombok.Getter;
import org.likelion.likelioncrudexcepvalid.common.error.ErrorCode;

@Getter
public class CustonException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustonException(ErrorCode error, String message) {
        super(message);
        this.errorCode = error;
    }

    public int getHttpStatus() {
        return errorCode.getHttpStatusCode();
    }
}
