package com.dasom.task.kakaoPay.service;

import com.dasom.task.kakaoPay.model.approval.Approval;
import com.dasom.task.kakaoPay.model.enumclass.ApprovalStatusCode;
import com.dasom.task.kakaoPay.model.enumclass.RequestStatusCode;
import com.dasom.task.kakaoPay.model.member.Member;
import com.dasom.task.kakaoPay.repository.approval.ApprovalRepository;
import com.dasom.task.kakaoPay.service.approve.ApprovalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.DateUtil.now;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
@SpringBootTest
@Transactional
public class ApprovalServiceTest {

    ApprovalService approvalService;
    ApprovalRepository approvalRepository;
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @BeforeEach
    public void beforeEach () {
        approvalRepository = new ApprovalRepository(sqlSessionTemplate);
        approvalService = new ApprovalService(approvalRepository);
    }

    public Approval setupFApproval(Member approveMember,  Member requestMember) {
        Approval approval = new Approval();
        approval.setTitle("테스트 신청서");
        approval.setContent("테스트 신청합니다.");
        approval.setApproveMember(approveMember);
        approval.setRequestMember(requestMember);
        approval.setApprovalStatusCode(ApprovalStatusCode.REQUEST);
        approval.setRequestStatusCode(RequestStatusCode.WAIT);
        approval.setRegisterDate(now());
        approval.setRegisterMemberId(4);
        return  approval;
    }

    @Test
    public void 기안목록조회 () {
        // given
        Approval.Search search = new Approval.Search();
        search.setApprovalId(1);
        search.setTitle("휴가신청서");
        search.setApproveMemberId(2);
        search.setRequestMemberId(4);
        search.setRequestStatusCode(RequestStatusCode.WAIT);
        search.setApprovalStatusCode(ApprovalStatusCode.REQUEST);

        // when
        List<Approval.ApprovalDocument> results = approvalService.getApprovalList(search);

        // then
        assertThat(results.get(0).getApprovalId()).isEqualTo(search.getApprovalId());
    }

    @Test
    public void 기안단건조회 () {
        // given
        Approval.Search search = new Approval.Search();
        search.setApprovalId(1);

        // when
        Approval.ApprovalDocument result = approvalService.getApproval(search);

        // then
        assertThat(result.getApprovalId()).isEqualTo(search.getApprovalId());
    }

    @Test
    public void 기안상신 () {
        // given
        Member approveMember = new Member(1, 1);
        Member requestMember = new Member(4, 4);
        Approval approval = setupFApproval(approveMember, requestMember);

        // when
        int approvalId = approvalService.registerApproval(approval);

        // then
        Approval.Search searchParam = new Approval.Search();
        searchParam.setApprovalId(approvalId);
        Approval.ApprovalDocument result = approvalService.getApproval(searchParam);

        assertThat(result.getApprovalId()).isEqualTo(searchParam.getApprovalId());
    }

    @Test
    public void 기안수정 () {
        // given
        Approval.AddParam addParam = new Approval.AddParam();
        addParam.setApprovalId(1);
        addParam.setApprovalStatusCode(ApprovalStatusCode.REQUEST);
        addParam.setRequestStatusCode(RequestStatusCode.WAIT);
        addParam.setContent("테스트 내용 수정합니다.");

        // when
        Integer approvalId = approvalService.updateApproval(addParam);

        // then
        Approval.Search search = new Approval.Search();
        search.setApprovalId(approvalId);
        Approval.ApprovalDocument result = approvalService.getApproval(search);

        assertThat(result.getContent()).isEqualTo(addParam.getContent());

    }

    @Test
    public void 기안삭제 () {
        // given
        Approval.AddParam addParam = new Approval.AddParam();
        addParam.setApprovalId(1);
        addParam.setApprovalStatusCode(ApprovalStatusCode.REQUEST);
        addParam.setRequestStatusCode(RequestStatusCode.WAIT);

        // when
        approvalService.deleteApproval(addParam);

        // then
        Approval.Search search = new Approval.Search();
        search.setApprovalId(1);
        Approval.ApprovalDocument result = approvalService.getApproval(search);
        assertNull(result);

    }
}
