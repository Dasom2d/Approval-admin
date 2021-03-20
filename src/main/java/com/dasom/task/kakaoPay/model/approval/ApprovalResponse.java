package com.dasom.task.kakaoPay.model.approval;


import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;

@Getter
@Setter
public class ApprovalResponse<T> {
    private String message;
    private int code;
    private T body;
    private int approvalId;

    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return this.code;
    }

    public T getBody() {
        return this.body;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String toString() {
        return "ApprovalResponse(message=" + this.getMessage() + ", code=" + this.getCode() + ", body=" + this.getBody() + ")";
    }

    @ConstructorProperties({"message", "code", "body", "approvalId"})
    private ApprovalResponse(String message, int code, T body, int approvalId) {
        this.message = message;
        this.code = code;
        this.body = body;
        this.approvalId = approvalId;
    }

    @ConstructorProperties({"message", "code", "body", "approvalId"})
    private ApprovalResponse(String message, int code, T body) {
        this.message = message;
        this.code = code;
        this.body = body;
    }

    public static <T> ApprovalResponse<T> of(String message, int code, T body, int approvalId) {
        return new ApprovalResponse(message, code, body, approvalId);
    }

    public static <T> ApprovalResponse<T> of(String message, int code, T body) {
        return new ApprovalResponse(message, code, body);
    }
}