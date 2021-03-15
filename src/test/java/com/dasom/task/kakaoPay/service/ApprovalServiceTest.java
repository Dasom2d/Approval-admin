package com.dasom.task.kakaoPay.service;

import com.dasom.task.kakaoPay.exception.ApprovalBadRequestException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    public Approval setupApproval(Member approveMember,  Member requestMember) {
        Approval approval = new Approval();
        approval.setTitle("테스트 신청서");
        approval.setContent("테스트 신청합니다.");
        approval.setApproveMember(approveMember);
        approval.setRequestMember(requestMember);
        approval.setApprovalStatusCode(ApprovalStatusCode.REQUEST);
        approval.setRequestStatusCode(RequestStatusCode.WAIT);
        approval.setRegisterDate(now());
        approval.setRegisterMemberId(4);
        approval.setApproveMemberInfo(approveMember);
        approval.setRequestMemberInfo(requestMember);
        return approval;
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
        Approval approval = setupApproval(approveMember, requestMember);

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
        Member approveMember = new Member(1, 1);
        Member requestMember = new Member(4, 4);
        Approval approval = setupApproval(approveMember, requestMember);
        approval.setApprovalId(1);
        approval.setContent("테스트 내용 수정합니다.");

        // when
        Integer approvalId = approvalService.updateApproval(approval);

        // then
        Approval.Search search = new Approval.Search();
        search.setApprovalId(approvalId);
        Approval.ApprovalDocument result = approvalService.getApproval(search);

        assertThat(result.getContent()).isEqualTo(approval.getContent());
    }

    @Test
    public void 기안삭제 () {
        // given
        // given
        Member approveMember = new Member(1, 1);
        Member requestMember = new Member(4, 4);
        Approval approval = setupApproval(approveMember, requestMember);
        approval.setApprovalId(1);

        // when
        approvalService.deleteApproval(approval);

        // then
        Approval.Search search = new Approval.Search();
        search.setApprovalId(1);
        Approval.ApprovalDocument result = approvalService.getApproval(search);
        assertNull(result);
    }

    @Test
    public void 직급상태예외_직급낮음 () {
        // given
        Member approveMember = new Member(4, 4);
        Member requestMember = new Member(1, 1);
        Approval approval = setupApproval(approveMember, requestMember);

        // when
        ApprovalBadRequestException e = assertThrows(ApprovalBadRequestException.class,
                () -> approvalService.registerApproval(approval));

        // then
        assertThat(e.getMessage()).isEqualTo("승인자는 요청자보다 직급이 높아야합니다.");
    }

    @Test
    public void 직급상태예외_직급같음 () {
        // given
        Member approveMember = new Member(4, 1);
        Member requestMember = new Member(4, 1);
        Approval approval = setupApproval(approveMember, requestMember);

        // when
        ApprovalBadRequestException e = assertThrows(ApprovalBadRequestException.class,
                () -> approvalService.registerApproval(approval));

        // then
        assertThat(e.getMessage()).isEqualTo("승인자는 요청자와 같은 직급일 수 없습니다.");
    }

    @Test
    public void 요청상태예외_수정 () {
        // given
        // given
        Member approveMember = new Member(1, 1);
        Member requestMember = new Member(4, 4);
        Approval approval = setupApproval(approveMember, requestMember);
        approval.setApprovalId(1);
        approval.setContent("테스트 내용 수정합니다.");
        approval.setApprovalStatusCode(ApprovalStatusCode.APPROVE);
        approval.setRequestStatusCode(RequestStatusCode.COMPLETE);

        // when
        ApprovalBadRequestException e = assertThrows(ApprovalBadRequestException.class,
                () -> approvalService.updateApproval(approval));

        // then
        assertThat(e.getMessage()).isEqualTo("요청 상태의 문서만 수정 혹은 삭제 가능합니다.");
    }
}
