package com.dasom.task.validation;

import com.dasom.task.kakaoPay.exception.ApprovalBadRequestException;
import com.dasom.task.kakaoPay.model.approval.Approval;
import com.dasom.task.kakaoPay.model.enumclass.code.ApprovalCode;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@Slf4j
public class ApprovalValidator {

    public void validate(Approval approval, Errors errors){
        if(StringUtils.isNullOrEmpty(approval.getTitle())) {
            log.error("title is empty");
            new ApprovalBadRequestException("제목을 입력해주세요.", ApprovalCode.INVALID_ADD_PARAM.getCode());
        }
        if(StringUtils.isNullOrEmpty(approval.getContent())) {
            log.error("content is empty");
            new ApprovalBadRequestException("내을 입력해주세요.", ApprovalCode.INVALID_ADD_PARAM.getCode());
        }
        if(approval.getRequestMemberId() == null) {
            log.error("request member id is empty");
            new ApprovalBadRequestException("요청자가 존재하지 않습니다.", ApprovalCode.INVALID_ADD_PARAM.getCode());
        }
        if(approval.getApproveMemberId() == null) {
            log.error("approve member id is empty");
            new ApprovalBadRequestException("승인자를 입력해주세요.", ApprovalCode.INVALID_ADD_PARAM.getCode());
        }
        if(isValidTitleLength(approval)) {
            log.error("title length is too long.");
            new ApprovalBadRequestException("제목의 길이는 45자를 넘을 수 없습니다.", ApprovalCode.INAPPOSITE_ADD_PARAM.getCode());
        }
        if(isValidContentLength(approval)) {
            log.error("title length is too long.");
            new ApprovalBadRequestException("제목의 길이는 3000자를 넘을 수 없습니다.", ApprovalCode.INAPPOSITE_ADD_PARAM.getCode());
        }

    }

    private boolean isValidTitleLength(Approval approval) {
        return approval.getTitle().length() <= 45;
    }

    private boolean isValidContentLength(Approval approval) {
        return approval.getContent().length() <= 3000;
    }
}
