package com.dasom.task.kakaoPay.validation.approval;

import com.dasom.task.kakaoPay.exception.ApprovalBadRequestException;
import com.dasom.task.kakaoPay.model.approval.Approval;
import com.dasom.task.kakaoPay.model.enumclass.ApprovalStatusCode;
import com.dasom.task.kakaoPay.model.enumclass.RequestStatusCode;
import com.dasom.task.kakaoPay.model.enumclass.code.ApprovalCode;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Slf4j
@Component
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


    public boolean isValidApproveGrade(Integer approveMemberGradeId, Integer requestMemberGradeId) {
        if(Approval.isSameGrade(approveMemberGradeId, requestMemberGradeId)){
            throw new ApprovalBadRequestException("승인자는 요청자와 같은 직급일 수 없습니다.", ApprovalCode.INAPPOSITE_APPROVE_MEMBER.getCode());
        }

        if(!Approval.isAvailableApproveGrade(approveMemberGradeId, requestMemberGradeId)){
            throw new ApprovalBadRequestException("승인자는 요청자보다 직급이 높아야합니다.", ApprovalCode.INAPPOSITE_APPROVE_MEMBER.getCode());
        }
        return true;
    }

    public void isValidUpdate(ApprovalStatusCode approvalStatusCode, RequestStatusCode requestStatusCode) {
        if(!Approval.isValidUpdate(approvalStatusCode, requestStatusCode)) {
            throw new ApprovalBadRequestException("요청 상태의 문서만 수정 혹은 삭제 가능합니다.", ApprovalCode.INAPPOSITE_APPROVAL_STATUS.getCode());
        }
    }
}
