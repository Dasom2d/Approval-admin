package com.dasom.task.kakaoPay.service.approve;

import com.dasom.task.kakaoPay.exception.ApprovalBadRequestException;
import com.dasom.task.kakaoPay.model.approval.Approval;
import com.dasom.task.kakaoPay.model.enumclass.ApprovalStatusCode;
import com.dasom.task.kakaoPay.model.enumclass.code.ApprovalCode;
import com.dasom.task.kakaoPay.repository.approval.ApprovalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ApprovalService {

    private ApprovalRepository approvalRepository;


    /**
     * 기안 리스트 조회
     */
    public List<Approval.ApprovalDocument> getApprovalList(Approval.Search search) {
        return approvalRepository.getApprovalList(search);
    }

    /**
     * 기안 조회
     */
    public Approval.ApprovalDocument getApproval(Approval.Search search) {
        return approvalRepository.getApproval(search);
    }


    /**
     * 기안 상신
     */
    @Transactional
    public void registerApproval(Approval.AddParam addParam) {
        isValidApproveGrade(addParam.getApproveMemberGradeId(), addParam.getRequestMemberGradeId());
        approvalRepository.registerApproval(addParam);
    }

    /**
     * 기안 수정
     */
    public void updateApproval(Approval.AddParam addParam) {
        isValidRequestStatus(addParam.getApproveStatusCode());
        approvalRepository.updateApproval(addParam);
    }

//
//    /**
//     * 기안 승인, 반려
//     */
//    public void processApproval(Approval approval) {
//        isValidRequestStatus(approval.getApproveStatusCode());
//        approvalRepository.updateApproval(approval);
//    }

    /**
     * 기안 삭제
     */
    public void deleteApproval(Approval.AddParam addParam) {
        isValidRequestStatus(addParam.getApproveStatusCode());
        approvalRepository.deleteApproval(addParam);
    }


    private void isValidApproveGrade(Integer approveMemberGradeId, Integer requestMemberGradeId) {
        if(!Approval.isAvailableApproveGrade(approveMemberGradeId, requestMemberGradeId)){
            throw new ApprovalBadRequestException("승인자는 요청자보다 직급이 높아야합니다.", ApprovalCode.INAPPOSITE_APPROVE_MEMBER.getCode());
        }

        if(!Approval.isSameGrade(approveMemberGradeId, requestMemberGradeId)){
            throw new ApprovalBadRequestException("승인자는 요청자와 같은 직급일 수 없습니다.", ApprovalCode.INAPPOSITE_APPROVE_MEMBER.getCode());
        }
    }

    private void isValidRequestStatus(ApprovalStatusCode approvalStatusCode) {
        if(!Approval.isRequestStatus(approvalStatusCode)) {
            throw new ApprovalBadRequestException("요청 상태의 문서만 수정 혹은 삭제 가능합니다.", ApprovalCode.INAPPOSITE_APPROVAL_STATUS.getCode());
        }
    }

//    private void isValidProcessMember()

}
