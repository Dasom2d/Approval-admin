package com.dasom.task.kakaoPay.service.approve;

import com.dasom.task.kakaoPay.model.approval.Approval;
import com.dasom.task.kakaoPay.model.enumclass.ApprovalStatusCode;
import com.dasom.task.kakaoPay.model.enumclass.RequestStatusCode;
import com.dasom.task.kakaoPay.repository.approval.ApprovalRepository;
import com.dasom.task.kakaoPay.validation.approval.ApprovalValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ApprovalService {

    private final ApprovalRepository approvalRepository;
    private final ApprovalValidator approvalValidator;

    public ApprovalService(ApprovalRepository approvalRepository, ApprovalValidator approvalValidator) {
        this.approvalRepository = approvalRepository;
        this.approvalValidator = approvalValidator;
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
    @Transactional
    public Integer registerApproval(Approval.Param param) {
        param.setApprovalStatusCode(ApprovalStatusCode.REQUEST);
        param.setRequestStatusCode(RequestStatusCode.WAIT);

        approvalValidator.register(param);
        approvalRepository.registerApproval(param);

        return param.getApprovalId();
    }

    /**
     * 기안 수정
     */
    @Transactional
    public Integer updateApproval(Approval.Param param) {
        approvalValidator.update(param);

        approvalRepository.updateApproval(param);

        return param.getApprovalId();
    }


    /**
     * 기안 승인, 반려
     */
    @Transactional
    public Integer processApproval(Approval.Param param) {
        approvalValidator.process(param);
        param.setRequestStatusCode(RequestStatusCode.COMPLETE);

        approvalRepository.processApproval(param);

        return param.getApprovalId();
    }

    /**
     * 기안 삭제
     */
    @Transactional
    public Integer deleteApproval(Approval.Param param) {
        approvalValidator.delete(param);

        approvalRepository.deleteApproval(param);
        return param.getApprovalId();
    }


}
