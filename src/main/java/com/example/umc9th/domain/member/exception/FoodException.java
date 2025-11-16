package com.example.umc9th.domain.member.exception;

import com.example.umc9th.domain.member.exception.code.FoodErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;

public class FoodException extends GeneralException {

    public FoodException(FoodErrorCode errorCode) {
        super(errorCode);
    }
}