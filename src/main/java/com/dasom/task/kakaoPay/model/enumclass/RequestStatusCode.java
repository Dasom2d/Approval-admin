package com.dasom.task.kakaoPay.model.enumclass;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RequestStatusCode {

    WAIT("wait", "대기"),
    COMPLETE("complete", "완료");


    final String statusCode;
    final String statusName;
}
