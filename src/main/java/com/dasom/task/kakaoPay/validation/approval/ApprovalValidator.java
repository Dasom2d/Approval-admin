package com.dasom.task.kakaoPay.validation.approval;

import com.dasom.task.kakaoPay.exception.ApprovalBadRequestException;
import com.dasom.task.kakaoPay.model.approval.Approval;
import com.dasom.task.kakaoPay.model.enumclass.ApprovalStatusCode;
import com.dasom.task.kakaoPay.model.enumclass.RequestStatusCode;
import com.dasom.task.kakaoPay.model.enumclass.code.ApprovalCode;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApprovalValidator {

    public void common(Approval.Param param){
        if(StringUtils.isNullOrEmpty(param.getTitle())) {
            log.error("title is empty");
            throw new ApprovalBadRequestException("제목을 입력해주세요.", ApprovalCode.INVALID_ADD_PARAM.getCode());
        }

        if(StringUtils.isNullOrEmpty(param.getContent())) {
            log.error("content is empty");
            throw new ApprovalBadRequestException("내용을 입력해주세요.", ApprovalCode.INVALID_ADD_PARAM.getCode());
        }

        if(param.getRequestMemberId() == null) {
            log.error("request member id is empty");
            throw new ApprovalBadRequestException("요청자가 존재하지 않습니다.", ApprovalCode.INVALID_ADD_PARAM.getCode());
        }

        if(param.getApproveMemberId() == null) {
            log.error("approve member id is empty");
            throw new ApprovalBadRequestException("승인자가 지정되지 않았습니다.", ApprovalCode.INVALID_ADD_PARAM.getCode());
        }

        if(isValidTitleLength(param.getTitle())) {
            log.error("title length is too long.");
            throw new ApprovalBadRequestException("제목의 길이는 45자를 넘을 수 없습니다.", ApprovalCode.INAPPOSITE_ADD_PARAM.getCode());
        }

        if(isValidContentLength(param.getContent())) {
            log.error("title length is too long.");
            throw new ApprovalBadRequestException("내용은 3000자를 넘을 수 없습니다.", ApprovalCode.INAPPOSITE_ADD_PARAM.getCode());
        }
    }

    public void register(Approval.Param param) {
        this.common(param);

        if(isSameGrade(param.getApproveMemberGradeId(), param.getRequestMemberGradeId())){
            throw new ApprovalBadRequestException("승인자는 요청자와 같은 직급일 수 없습니다.", ApprovalCode.INAPPOSITE_APPROVE_MEMBER.getCode());
        }

        if(!isAvailableApproveGrade(param.getApproveMemberGradeId(), param.getRequestMemberGradeId())){
            throw new ApprovalBadRequestException("승인자는 요청자보다 직급이 높아야합니다.", ApprovalCode.INAPPOSITE_APPROVE_MEMBER.getCode());
        }
    }

    public void update(Approval.Param param) {
        this.common(param);

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

    private boolean isValidTitleLength(String title) {
        return title.length() >= 45;
    }

    private boolean isValidContentLength(String content) {
        return content.length() >= 3000;
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
