package com.dasom.task.kakaoPay.service.approve;

import com.dasom.task.kakaoPay.model.approval.Approval;
import com.dasom.task.kakaoPay.model.enumclass.ApprovalStatusCode;
import com.dasom.task.kakaoPay.model.enumclass.RequestStatusCode;
import com.dasom.task.kakaoPay.repository.approval.ApprovalRepository;
import com.dasom.task.kakaoPay.validation.approval.ApprovalValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ApprovalService {

    private ApprovalRepository approvalRepository;

    @Autowired
    private ApprovalValidator approvalValidator;

    public ApprovalService(ApprovalRepository approvalRepository) {
        this.approvalRepository = approvalRepository;
    }

    /**
     * 기안 리스트 조회
     */
    public List<Approval> getApprovalList(Approval.Search search) {

        return approvalRepository.getApprovalList(search);
    }

    /**
     * 기안 조회
     */
    public Approval getApproval(Approval.Search search) {
        return approvalRepository.getApproval(search);
    }


    /**
     * 기안 상신
     */
    public Integer registerApproval(Approval.Param param) {
        param.setApprovalStatusCode(ApprovalStatusCode.REQUEST);
        param.setRequestStatusCode(RequestStatusCode.WAIT);
        // 고쳐야함
        param.setRegisterMemberId(2);

        approvalValidator.isValidApproveGrade(param.getApproveMemberGradeId(), param.getRequestMemberGradeId());
        approvalRepository.registerApproval(param);
        return param.getApprovalId();
    }

    /**
     * 기안 수정
     */
    public Integer updateApproval(Approval.Param param) {
        approvalValidator.isValidApproveGrade(param.getApproveMemberGradeId(), param.getRequestMemberGradeId());
        approvalValidator.isValidUpdateDelete(param.getApprovalStatusCode(), param.getRequestStatusCode());
        approvalRepository.updateApproval(param);
        return param.getApprovalId();
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
    public Integer deleteApproval(Approval.Param param) {
        approvalValidator.isValidUpdateDelete(param.getApprovalStatusCode(), param.getRequestStatusCode());
        approvalRepository.deleteApproval(param);
        return param.getApprovalId();
    }


}
