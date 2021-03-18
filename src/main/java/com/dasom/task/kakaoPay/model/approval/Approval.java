package com.dasom.task.kakaoPay.model.approval;

import com.dasom.task.kakaoPay.model.enumclass.ApprovalStatusCode;
import com.dasom.task.kakaoPay.model.enumclass.RequestStatusCode;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Approval {

    private Integer approvalId;
    private String title;
    private String content;
    private String approveMemberName;
    private String requestMemberName;
    private Integer approveMemberId;
    private Integer requestMemberId;
    private Integer approveMemberGradeId;
    private Integer requestMemberGradeId;
    private ApprovalStatusCode approvalStatusCode;
    private RequestStatusCode requestStatusCode;
    private Date approveDate;
    private Date registerDate;
    private Date modifyDate;
    private Integer registerMemberId;

    @Getter
    @Setter
    public static class Param {
        private Integer approvalId;
        private String title;
        private String content;
        private Integer approveMemberId;
        private Integer requestMemberId;
        private Integer approveMemberGradeId;
        private Integer requestMemberGradeId;
        private ApprovalStatusCode approvalStatusCode;
        private RequestStatusCode requestStatusCode;
        private Integer registerMemberId;
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

}
