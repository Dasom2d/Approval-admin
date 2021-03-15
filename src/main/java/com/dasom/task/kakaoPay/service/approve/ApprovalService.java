package com.dasom.task.kakaoPay.service.approve;

import com.dasom.task.kakaoPay.exception.ApprovalBadRequestException;
import com.dasom.task.kakaoPay.model.approval.Approval;
import com.dasom.task.kakaoPay.model.enumclass.ApprovalStatusCode;
import com.dasom.task.kakaoPay.model.enumclass.RequestStatusCode;
import com.dasom.task.kakaoPay.model.enumclass.code.ApprovalCode;
import com.dasom.task.kakaoPay.model.member.Member;
import com.dasom.task.kakaoPay.repository.approval.ApprovalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ApprovalService {

    private ApprovalRepository approvalRepository;

    public ApprovalService(ApprovalRepository approvalRepository) {
        this.approvalRepository = approvalRepository;
    }


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
        isValidApproveGrade(approval.getRequestMember(), approval.getApproveMember());

        // 고쳐야 함
        Member member = new Member(approval.getRegisterMemberId());
        Approval.Param param = makeApprovalParam(approval, member);

        approvalRepository.registerApproval(param);
        return param.getApprovalId();
    }

    /**
     * 기안 수정
     */
    public Integer updateApproval(Approval.Param param) {
        isValidUpdate(param.getApprovalStatusCode(), param.getRequestStatusCode());
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
        isValidUpdate(param.getApprovalStatusCode(), param.getRequestStatusCode());
        approvalRepository.deleteApproval(param);
        return param.getApprovalId();
    }

    public static Approval.Param makeApprovalParam(Approval approval, Member member) {
            return Approval.Param.builder()
                    .approvalId(approval.getApprovalId())
                    .title(approval.getTitle())
                    .content(approval.getContent())
                    .approveMemberGradeId(approval.getApproveMember().getGradeId())
                    .approveMemberId(approval.getApproveMember().getMemberId())
                    .requestMemberGradeId(approval.getRequestMember().getGradeId())
                    .requestMemberId(approval.getRequestMember().getMemberId())
                    .approvalStatusCode(approval.getApprovalStatusCode())
                    .requestStatusCode(approval.getRequestStatusCode())
                    .registerDate(new Date())
                    .registerMemberId(member.getMemberId())
                    .build();
    }

    private boolean isValidApproveGrade(Member approveMember, Member requestMember) {
        Integer approveMemberGradeId = approveMember.getGradeId();
        Integer requestMemberGradeId = requestMember.getGradeId();

        if(Approval.isSameGrade(approveMemberGradeId, requestMemberGradeId)){
            throw new ApprovalBadRequestException("승인자는 요청자와 같은 직급일 수 없습니다.", ApprovalCode.INAPPOSITE_APPROVE_MEMBER.getCode());
        }

        if(!Approval.isAvailableApproveGrade(approveMemberGradeId, requestMemberGradeId)){
            throw new ApprovalBadRequestException("승인자는 요청자보다 직급이 높아야합니다.", ApprovalCode.INAPPOSITE_APPROVE_MEMBER.getCode());
        }
        return true;
    }

    private void isValidUpdate(ApprovalStatusCode approvalStatusCode, RequestStatusCode requestStatusCode) {
        if(!Approval.isValidUpdate(approvalStatusCode, requestStatusCode)) {
            throw new ApprovalBadRequestException("요청 상태의 문서만 수정 혹은 삭제 가능합니다.", ApprovalCode.INAPPOSITE_APPROVAL_STATUS.getCode());
        }
    }

//    private void isValidProcessMember()

}
