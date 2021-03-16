package com.dasom.task.kakaoPay.model.enumclass.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApprovalCode {
    FIND_SUCCESS("approval.find.success", 0),

    REGISTER_SUCCESS("approval.register.success", 0),
    UPDATE_SUCCESS("approval.register.update", 0),
    APPROVE_SUCCESS("approval.register.approve", 0),
    DELETE_SUCCESS("approval.register.delete", 0),

    INVALID_ADD_PARAM("approval.invalid.add.param", -1000),
    INAPPOSITE_ADD_PARAM("approval.inapposite.add.param", -1001),

    INAPPOSITE_APPROVE_MEMBER("approval.inapposite.approve.member", -2000),

    INAPPOSITE_APPROVAL_STATUS("approval.approval.status", -3000);


    private final String messageCode;
    private final int code;
}
