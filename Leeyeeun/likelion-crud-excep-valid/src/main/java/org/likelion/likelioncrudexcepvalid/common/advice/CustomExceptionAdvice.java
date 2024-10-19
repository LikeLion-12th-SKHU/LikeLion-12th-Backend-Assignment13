package org.likelion.likelioncrudexcepvalid.common.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.likelion.likelioncrudexcepvalid.common.dto.BaseResponse;
import org.likelion.likelioncrudexcepvalid.common.error.ErrorCode;
import org.likelion.likelioncrudexcepvalid.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@Component
@RequiredArgsConstructor
public class CustomExceptionAdvice {

    // 500
    // 원인 모를 이유
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public BaseResponse handlerServerException(final Exception e) {
        log.error("Internal Server Error: {}", e.getMessage(), e);
        return BaseResponse.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    // 400
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<Map<String, String>> handlerValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return BaseResponse.error(ErrorCode.VALIDATION_ERROR, converMapToString(errorMap));
    }

    private String converMapToString(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();

        for(Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(" : ").append(entry.getValue()).append(" , ");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // 내부 커스텀 에러
    @ExceptionHandler(CustomException.class)
    public BaseResponse handlerCustomException(CustomException e) {
        log.error("Custom Exception: {}", e.getMessage(), e);
        return BaseResponse.error(e.getErrorCode(), e.getMessage());
    }

}
