package com.dasom.task.kakaoPay.model.approval;

import com.dasom.task.kakaoPay.model.enumclass.ApprovalStatusCode;
import com.dasom.task.kakaoPay.model.enumclass.RequestStatusCode;
import com.dasom.task.kakaoPay.model.member.Member;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Approval {

    private Integer approvalId;
    private String title;
    private String content;
    private Member requestMember;
    private Member approveMember;
    private Integer approveMemberGradeId;
    private Integer requestMemberGradeId;
    private Integer approveMemberId;
    private Integer requestMemberId;
    private ApprovalStatusCode approvalStatusCode;
    private RequestStatusCode requestStatusCode;
    private Date approveDate;
    private Date registerDate;
    private Date modifyDate;
    private Integer registerMemberId;

    public void setRequestMemberInfo(Member requestMember) {
        this.requestMemberGradeId = requestMember.getGradeId();
        this.requestMemberId = requestMember.getMemberId();
    }

    public void setApproveMemberInfo(Member approveMember) {
        this.approveMemberGradeId = approveMember.getGradeId();
        this.approveMemberId = approveMember.getMemberId();
    }

    @Getter
    @Setter
    public static class AddParam {
        private String title;
        private String content;
        private Member requestMember;
        private Member approveMember;
        private ApprovalStatusCode approvalStatusCode;
        private RequestStatusCode requestStatusCode;

        @Getter
        @Setter
        public static class Member {
            private Integer memberId;
            private String name;
            private Integer gradeId;
        }
    }


    @Data
    public static class Search {
        private Integer approvalId;
        private String title;
        private Integer requestMemberId;
        private Integer approveMemberId;
        private ApprovalStatusCode approvalStatusCode;
        private RequestStatusCode requestStatusCode;
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
        private Date approveDate;
        private Date registerDate;
        private Boolean isCompleted;
    }

    public static boolean isAvailableApproveGrade (Integer approveMemberGradeId, Integer requestMemberGradeId) {
        return approveMemberGradeId - requestMemberGradeId < 0;
    }

    public static boolean isSameGrade (Integer approveMemberGradeId, Integer requestMemberGradeId) {
        return approveMemberGradeId - requestMemberGradeId == 0;
    }

    public static boolean isValidUpdate(ApprovalStatusCode approvalStatusCode, RequestStatusCode requestStatusCode){
        return approvalStatusCode.equals(ApprovalStatusCode.REQUEST) && requestStatusCode.equals(RequestStatusCode.WAIT);
    }

}
