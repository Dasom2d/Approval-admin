package com.dasom.task.kakaoPay.model.approval;

import com.dasom.task.kakaoPay.model.enumclass.ApprovalStatusCode;
import com.dasom.task.kakaoPay.model.enumclass.RequestStatusCode;
import com.dasom.task.kakaoPay.model.member.Member;
import lombok.Data;

import java.util.Date;

@Data
public class Approval {

    private Integer approvalId;
    private String title;
    private String content;
    private Member requestMember;
    private Member approveMember;
    private ApprovalStatusCode approveStatusCode;
    private RequestStatusCode requestStatusCode;
    private Date approveDate;
    private Date registerDate;
    private Date modifyDate;
    private Integer registerMemberId;

    @Data
    public static class Search {
        private Integer approvalId;
        private String title;
        private Integer registerMemberId;
        private Integer approveMemberId;
        private ApprovalStatusCode approveStatusCode;
        private RequestStatusCode requestStatusCode;
    }

    @Data
    public static class AddParam {
        private Integer approvalId;
        private String title;
        private String content;
        private Integer approveMemberGradeId;
        private Integer requestMemberGradeId;
        private Integer approveMemberId;
        private Integer requestMemberId;
        private ApprovalStatusCode approveStatusCode;
        private RequestStatusCode requestStatusCode;
        private Date approveDate;
        private Date registerDate;
        private Integer registerMemberId;
    }

    @Data
    public static class ApprovalDocument {
        private Integer approvalId;
        private String title;
        private String content;
        private String requestMemberName;
        private String approveMemberName;
        private RequestStatusCode requestStatusCode;
        private ApprovalStatusCode approveStatusCode;
        private Boolean isCompleted;
        private Date approveDate;
        private Date registerDate;
    }

    public static boolean isAvailableApproveGrade(Integer approveMemberGradeId, Integer requestMemberGradeId) {
        return approveMemberGradeId - requestMemberGradeId > 0;
    }

    public static boolean isSameGrade(Integer approveMemberGradeId, Integer requestMemberGradeId) { ;
        return approveMemberGradeId - requestMemberGradeId == 0;
    }

    public static boolean isRequestStatus(ApprovalStatusCode approvalStatusCode){
        return approvalStatusCode.equals(ApprovalStatusCode.REQUEST);
    }


}
