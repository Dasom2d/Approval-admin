package com.dasom.task.kakaoPay.service.approve;

import com.dasom.task.kakaoPay.model.approval.Approval;
import com.dasom.task.kakaoPay.repository.approval.ApprovalRepository;
import com.dasom.task.kakaoPay.validation.approval.ApprovalValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ApprovalService {

    private ApprovalRepository approvalRepository;

    public ApprovalService(ApprovalRepository approvalRepository) {
        this.approvalRepository = approvalRepository;
    }

    @Autowired
    private ApprovalValidator approvalValidator;

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
    public Integer registerApproval(Approval approval) {
        approvalValidator.isValidApproveGrade(approval.getApproveMemberGradeId(), approval.getRequestMemberGradeId());

        approvalRepository.registerApproval(approval);
        return approval.getApprovalId();
    }

    /**
     * 기안 수정
     */
    public Integer updateApproval(Approval approval) {
        approvalValidator.isValidApproveGrade(approval.getApproveMemberGradeId(), approval.getRequestMemberGradeId());
        approvalValidator.isValidUpdate(approval.getApprovalStatusCode(), approval.getRequestStatusCode());
        approvalRepository.updateApproval(approval);
        return approval.getApprovalId();
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
    public Integer deleteApproval(Approval approval) {
        approvalValidator.isValidApproveGrade(approval.getApproveMemberGradeId(), approval.getRequestMemberGradeId());
        approvalValidator.isValidUpdate(approval.getApprovalStatusCode(), approval.getRequestStatusCode());
        approvalRepository.deleteApproval(approval);
        return approval.getApprovalId();
    }


}
