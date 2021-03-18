package com.dasom.task.kakaoPay.exception;

import lombok.Getter;

@Getter
public class ApprovalException extends RuntimeException{
    int code;

    public ApprovalException(String message, int code) {
        super(message);
        this.code = code;
    }
}
