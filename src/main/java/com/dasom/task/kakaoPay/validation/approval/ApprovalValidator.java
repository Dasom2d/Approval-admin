package com.dasom.task.kakaoPay.validation.approval;

import com.dasom.task.kakaoPay.exception.ApprovalBadRequestException;
import com.dasom.task.kakaoPay.model.approval.Approval;
import com.dasom.task.kakaoPay.model.enumclass.ApprovalStatusCode;
import com.dasom.task.kakaoPay.model.enumclass.RequestStatusCode;
import com.dasom.task.kakaoPay.model.enumclass.code.ApprovalCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApprovalValidator {

    public void register(Approval.Param param) {
        if(isSameGrade(param.getApproveMemberGradeId(), param.getRequestMemberGradeId())){
            throw new ApprovalBadRequestException("승인자는 요청자와 같은 직급일 수 없습니다.", ApprovalCode.INAPPOSITE_APPROVE_MEMBER.getCode());
        }

        if(!isAvailableApproveGrade(param.getApproveMemberGradeId(), param.getRequestMemberGradeId())){
            throw new ApprovalBadRequestException("승인자는 요청자보다 직급이 높아야합니다.", ApprovalCode.INAPPOSITE_APPROVE_MEMBER.getCode());
        }
    }

    public void update(Approval.Param param) {
        if(!(isValidApprovalStatusCode(param.getApprovalStatusCode()) && isValidRequestStatusCode(param.getRequestStatusCode()))) {
            throw new ApprovalBadRequestException("승인 요청 상태의 문서만 수정 가능합니다.", ApprovalCode.INAPPOSITE_STATUS.getCode());
        }
    }

    public void process(Approval.Param param) {
        if(!isValidRequestStatusCode(param.getRequestStatusCode())) {
            throw new ApprovalBadRequestException("대기 상태의 문서만 승인 혹은 반려 가능합니다.", ApprovalCode.INAPPOSITE_STATUS.getCode());
        }
    }

    public void delete(Approval.Param param) {
        if(!(isValidApprovalStatusCode(param.getApprovalStatusCode()) && isValidRequestStatusCode(param.getRequestStatusCode()))) {
            throw new ApprovalBadRequestException("승인 요청 상태의 문서만 삭제 가능합니다.", ApprovalCode.INAPPOSITE_STATUS.getCode());
        }
    }


    public boolean isAvailableApproveGrade (Integer approveMemberGradeId, Integer requestMemberGradeId) {
        return approveMemberGradeId - requestMemberGradeId < 0;
    }

    public boolean isSameGrade (Integer approveMemberGradeId, Integer requestMemberGradeId) {
        return approveMemberGradeId - requestMemberGradeId == 0;
    }

    public boolean isValidApprovalStatusCode(ApprovalStatusCode approvalStatusCode){
        return approvalStatusCode.equals(ApprovalStatusCode.REQUEST);
    }

    public boolean isValidRequestStatusCode(RequestStatusCode requestStatusCode){
        return requestStatusCode.equals(RequestStatusCode.WAIT);
    }

}
