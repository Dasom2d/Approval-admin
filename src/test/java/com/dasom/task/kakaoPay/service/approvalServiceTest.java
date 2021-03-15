package com.dasom.task.kakaoPay.service;

import com.dasom.task.kakaoPay.model.approval.Approval;
import com.dasom.task.kakaoPay.model.enumclass.ApprovalStatusCode;
import com.dasom.task.kakaoPay.model.enumclass.RequestStatusCode;
import com.dasom.task.kakaoPay.model.member.Member;
import com.dasom.task.kakaoPay.repository.approval.ApprovalRepository;
import com.dasom.task.kakaoPay.service.approve.ApprovalService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.MAP;
import static org.assertj.core.util.DateUtil.now;

@Slf4j
@SpringBootTest
public class approvalServiceTest {

    ApprovalService approvalService;
    ApprovalRepository approvalRepository;

    @BeforeEach
    public void beforeEach () {
        approvalRepository = new ApprovalRepository();
        approvalService = new ApprovalService(approvalRepository);
    }

    public Approval setupFApproval() {
        Approval approval = new Approval();
        approval.setTitle("테스트 신청서");
        approval.setContent("테스트 신청합니다.");
        approval.setApprovalStatusCode(ApprovalStatusCode.REQUEST);
        approval.setRequestStatusCode(RequestStatusCode.WAIT);
        approval.setRegisterDate(now());
        approval.setRegisterMemberId(4);
        return  approval;
    }



    @Test
    public void 기안상신 () {
        // given
        Member approveMember = new Member(1, 1);
        Member requestMember = new Member(4, 4);
        Approval approval = setupFApproval();

        // when
        approvalService.registerApproval(approval);

        // then
        Approval.Search searchParam = new Approval.Search();
        searchParam.setApprovalId(approval.getApprovalId());
        Approval.ApprovalDocument result = approvalService.getApproval(searchParam);

        assertThat(result.getApprovalId()).isEqualTo(searchParam.getApprovalId());
    }
}
