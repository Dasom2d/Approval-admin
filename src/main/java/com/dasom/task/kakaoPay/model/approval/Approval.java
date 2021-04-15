package com.dasom.task.kakaoPay.model.approval;

import com.dasom.task.kakaoPay.model.enumclass.ApprovalStatusCode;
import com.dasom.task.kakaoPay.model.enumclass.RequestStatusCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Builder
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime approveDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime registerDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime modifyDate;
    private Integer registerMemberId;

    @Builder
    @Getter
    @Setter
    public static class Param {
        private Integer approvalId;
        @NotNull(message = "제목을 입력해주세요.")
        @Size(max = 45, message = "제목의 길이는 45자를 넘을 수 없습니다.")
        private String title;
        @NotNull(message = "내용을 입력해주세요.")
        @Size(max = 3000, message = "내의 길이는 3000자를 넘을 수 없습니다.")
        private String content;
        @NotNull(message = "승인자가 지정되지 않았습니다.")
        private Integer approveMemberId;
        @NotNull(message = "요청자가 존재하지 않습니다.")
        private Integer requestMemberId;
        private Integer approveMemberGradeId;
        private Integer requestMemberGradeId;
        private ApprovalStatusCode approvalStatusCode;
        private RequestStatusCode requestStatusCode;
        private Integer registerMemberId;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Search {
        private Integer approvalId;
        private String title;
        private Integer requestMemberId;
        private Integer approveMemberId;
        private ApprovalStatusCode approvalStatusCode;
        private RequestStatusCode requestStatusCode;
        private Integer registerMemberId;
    }

    public Approval toApproval(Approval.Search search) {
        Approval result = new Approval();
        result.setApprovalId(search.approvalId);
        result.setTitle(search.title);
        result.setRequestMemberId(search.requestMemberId);
        result.setApproveMemberId(search.approveMemberId);
        result.setRequestStatusCode(search.requestStatusCode);
        result.setApprovalStatusCode(search.approvalStatusCode);

        return result;
    }

}
