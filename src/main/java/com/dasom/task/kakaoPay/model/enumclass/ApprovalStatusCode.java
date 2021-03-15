package com.dasom.task.kakaoPay.model.enumclass;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ApprovalStatusCode {

    REQUEST("request", "상신"),
    APPROVE("approve", "승인"),
    RETURN("return", "반려");

    final String statusCode;
    final String statusName;

}
