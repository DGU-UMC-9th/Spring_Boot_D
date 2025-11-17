package com.example.umc9th.global.apiPayload;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private final boolean isSuccess;
    private final String code;
    private final String message;
    private final T result;

    //성공 (데이터 포함)
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode successCode, T data) {
        return new ApiResponse<>(
                true,
                successCode.getCode(),
                successCode.getMessage(),
                data
        );
    }

    //성공 (데이터 없음)
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode successCode) {
        return new ApiResponse<>(
                true,
                successCode.getCode(),
                successCode.getMessage(),
                null
        );
    }

    //실패 (데이터 포함)
    public static <T> ApiResponse<T> onFailure(BaseErrorCode errorCode, T data) {
        return new ApiResponse<>(
                false,
                errorCode.getCode(),
                errorCode.getMessage(),
                data
        );
    }

    //실패 (데이터 없음)
    public static <T> ApiResponse<T> onFailure(BaseErrorCode errorCode) {
        return new ApiResponse<>(
                false,
                errorCode.getCode(),
                errorCode.getMessage(),
                null
        );
    }
}