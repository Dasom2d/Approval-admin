package com.dasom.task.kakaoPay.exception;

import lombok.Getter;

@Getter
public class ApprovalBadRequestException extends RuntimeException {

    private int code;

    public ApprovalBadRequestException(String message, int code) {
        super(message);
        this.code = code;
    }
}
