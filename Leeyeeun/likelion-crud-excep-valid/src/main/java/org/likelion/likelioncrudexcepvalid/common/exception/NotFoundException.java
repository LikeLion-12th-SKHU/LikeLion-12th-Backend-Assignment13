package org.likelion.likelioncrudexcepvalid.common.exception;

import org.likelion.likelioncrudexcepvalid.common.error.ErrorCode;

public class NotFoundException extends CustomException {
    public NotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
