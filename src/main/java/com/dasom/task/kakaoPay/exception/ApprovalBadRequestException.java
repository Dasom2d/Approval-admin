package com.dasom.task.kakaoPay.exception;

import lombok.Getter;

@Getter
public class ApprovalBadRequestException extends RuntimeException {

    private int code;
    private String message;

    public ApprovalBadRequestException(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
