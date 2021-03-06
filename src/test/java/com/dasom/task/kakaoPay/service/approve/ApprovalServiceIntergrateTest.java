package com.dasom.task.kakaoPay.service.approve;

import com.dasom.task.kakaoPay.exception.ApprovalBadRequestException;
import com.dasom.task.kakaoPay.model.approval.Approval;
import com.dasom.task.kakaoPay.model.enumclass.ApprovalStatusCode;
import com.dasom.task.kakaoPay.model.enumclass.RequestStatusCode;
import com.dasom.task.kakaoPay.repository.approval.ApprovalRepository;
import com.dasom.task.kakaoPay.validation.approval.ApprovalValidator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class ApprovalServiceIntergrateTest {

    ApprovalService approvalService;
    ApprovalRepository approvalRepository;
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    @Autowired
    ApprovalValidator approvalValidator;

    @BeforeEach
    public void beforeEach () {
        approvalRepository = new ApprovalRepository(sqlSessionTemplate);
        approvalService = new ApprovalService(approvalRepository, approvalValidator);
    }
    
    private Approval.Search searchSetup() {
        return Approval.Search.builder()
                .approvalId(1)
                .requestMemberId(2)
                .approveMemberId(4)
                .approvalStatusCode(ApprovalStatusCode.REQUEST)
                .requestStatusCode(RequestStatusCode.WAIT)
                .registerMemberId(2)
                .build();
    }

    private Approval.Param paramSetup() {
        return Approval.Param.builder()
                .title("????????? ??????")
                .content("????????? ??????")
                .approveMemberId(1)
                .requestMemberId(4)
                .approveMemberGradeId(1)
                .requestMemberGradeId(4)
                .approvalStatusCode(ApprovalStatusCode.REQUEST)
                .requestStatusCode(RequestStatusCode.WAIT)
                .registerMemberId(4)
                .build();
    }

    @Test
    public void ?????????????????? () {
        // given
        Approval.Search search = new Approval.Search();
        search.setTitle("???????????????");

        // when
        List<Approval> results = approvalService.getApprovalList(search);

        // then
        assertThat(results.get(0).getTitle()).isEqualTo(search.getTitle());
    }

    @Test
    public void ?????????????????? () {
        // given
        Approval.Search search = searchSetup();
        search.setApprovalId(1);

        // when
        Approval result = approvalService.getApproval(search);

        // then
        assertThat(result.getApprovalId()).isEqualTo(search.getApprovalId());
    }

    @Test
    public void ???????????? () {
        // given
        Approval.Param param = paramSetup();

        // when
        int approvalId = approvalService.registerApproval(param);

        // then
        Approval.Search searchParam = searchSetup();
        searchParam.setApprovalId(approvalId);
        Approval result = approvalService.getApproval(searchParam);

        assertThat(result.getApprovalId()).isEqualTo(searchParam.getApprovalId());
    }

    @Test
    public void ???????????? () {
        // given
        Approval.Param param = paramSetup();
        param.setApprovalId(1);
        param.setContent("????????? ?????? ???????????????.");

        // when
        Integer approvalId = approvalService.updateApproval(param);

        // then
        Approval.Search search = searchSetup();
        search.setApprovalId(approvalId);
        Approval result = approvalService.getApproval(search);

        assertThat(result.getContent()).isEqualTo(param.getContent());
    }

    @Test
    public void ???????????? () {
        // given
        Approval.Param param = paramSetup();
        param.setApprovalId(1);
        param.setApprovalStatusCode(ApprovalStatusCode.APPROVE);

        // when
        Integer approvalId = approvalService.processApproval(param);

        // then
        Approval.Search search = searchSetup();
        search.setApprovalId(approvalId);
        ApprovalStatusCode result = approvalService.getApproval(search).getApprovalStatusCode();
        assertThat(result).isEqualTo(param.getApprovalStatusCode());
    }

    @Test
    public void ???????????? () {
        // given
        Approval.Param param = paramSetup();
        param.setApprovalId(1);

        // when
        approvalService.deleteApproval(param);

        // then
        Approval.Search search = searchSetup();
        search.setApprovalId(1);
        Approval result = approvalService.getApproval(search);
        assertNull(result);
    }

    @Test
    public void ????????????_???????????? () {
        // given
        Approval.Param param = paramSetup();
        param.setTitle(null);

        // when
        ApprovalBadRequestException e = assertThrows(ApprovalBadRequestException.class,
                () -> approvalService.registerApproval(param));

        // then
        assertThat(e.getMessage()).isEqualTo("????????? ??????????????????.");
    }

    @Test
    public void ????????????_???????????? () {
        // given
        Approval.Param param = paramSetup();
        param.setContent(null);

        // when
        ApprovalBadRequestException e = assertThrows(ApprovalBadRequestException.class,
                () -> approvalService.registerApproval(param));

        // then
        assertThat(e.getMessage()).isEqualTo("????????? ??????????????????.");
    }

    @Test
    public void ????????????_????????????????????? () {
        // given
        Approval.Param param = paramSetup();
        param.setRequestMemberId(null);

        // when
        ApprovalBadRequestException e = assertThrows(ApprovalBadRequestException.class,
                () -> approvalService.registerApproval(param));

        // then
        assertThat(e.getMessage()).isEqualTo("???????????? ???????????? ????????????.");
    }

    @Test
    public void ????????????_????????????????????? () {
        // given
        Approval.Param param = paramSetup();
        param.setApproveMemberId(null);

        // when
        ApprovalBadRequestException e = assertThrows(ApprovalBadRequestException.class,
                () -> approvalService.registerApproval(param));

        // then
        assertThat(e.getMessage()).isEqualTo("???????????? ???????????? ???????????????.");
    }

    @Test
    public void ????????????_???????????? () {
        // given
        Approval.Param param = paramSetup();
        param.setTitle("???????????????45????????????????????????.???????????????45????????????????????????.???????????????45????????????????????????.");

        // when
        ApprovalBadRequestException e = assertThrows(ApprovalBadRequestException.class,
                () -> approvalService.registerApproval(param));

        // then
        assertThat(e.getMessage()).isEqualTo("????????? ????????? 45?????? ?????? ??? ????????????.");
    }

    @Test
    public void ??????????????????_???????????? () {
        // given
        Approval.Param param = paramSetup();
        param.setApproveMemberGradeId(4);
        param.setRequestMemberGradeId(1);

        // when
        ApprovalBadRequestException e = assertThrows(ApprovalBadRequestException.class,
                () -> approvalService.registerApproval(param));

        // then
        assertThat(e.getMessage()).isEqualTo("???????????? ??????????????? ????????? ??????????????????.");
    }

    @Test
    public void ??????????????????_???????????? () {
        // given
        Approval.Param param = paramSetup();
        param.setApproveMemberGradeId(1);
        param.setRequestMemberGradeId(1);

        // when
        ApprovalBadRequestException e = assertThrows(ApprovalBadRequestException.class,
                () -> approvalService.registerApproval(param));

        // then
        assertThat(e.getMessage()).isEqualTo("???????????? ???????????? ?????? ????????? ??? ????????????.");
    }

    @Test
    public void ??????????????????_?????? () {
        // given
        Approval.Param param = paramSetup();
        param.setApprovalId(1);
        param.setApprovalStatusCode(ApprovalStatusCode.APPROVE);
        param.setRequestStatusCode(RequestStatusCode.COMPLETE);

        // when
        ApprovalBadRequestException e = assertThrows(ApprovalBadRequestException.class,
                () -> approvalService.updateApproval(param));

        // then
        assertThat(e.getMessage()).isEqualTo("?????? ?????? ????????? ????????? ?????? ???????????????.");
    }

    @Test
    public void ??????????????????_?????? () {
        // given
        Approval.Param param = paramSetup();
        param.setApprovalId(1);
        param.setApprovalStatusCode(ApprovalStatusCode.APPROVE);
        param.setRequestStatusCode(RequestStatusCode.COMPLETE);

        // when
        ApprovalBadRequestException e = assertThrows(ApprovalBadRequestException.class,
                () -> approvalService.deleteApproval(param));

        // then
        assertThat(e.getMessage()).isEqualTo("?????? ?????? ????????? ????????? ?????? ???????????????.");
    }

    @Test
    public void ?????????????????? () {
        // given
        Approval.Param param = paramSetup();
        param.setApprovalId(1);
        param.setRequestStatusCode(RequestStatusCode.COMPLETE);

        // when
        ApprovalBadRequestException e = assertThrows(ApprovalBadRequestException.class,
                () -> approvalService.processApproval(param));

        // then
        assertThat(e.getMessage()).isEqualTo("?????? ????????? ????????? ?????? ?????? ?????? ???????????????.");
    }

}
