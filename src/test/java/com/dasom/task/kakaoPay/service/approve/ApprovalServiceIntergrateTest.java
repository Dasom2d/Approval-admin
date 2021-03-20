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
                .title("테스트 제목")
                .content("테스트 내용")
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
    public void 기안목록조회 () {
        // given
        Approval.Search search = new Approval.Search();
        search.setTitle("휴가신청서");

        // when
        List<Approval> results = approvalService.getApprovalList(search);

        // then
        assertThat(results.get(0).getTitle()).isEqualTo(search.getTitle());
    }

    @Test
    public void 기안단건조회 () {
        // given
        Approval.Search search = searchSetup();
        search.setApprovalId(1);

        // when
        Approval result = approvalService.getApproval(search);

        // then
        assertThat(result.getApprovalId()).isEqualTo(search.getApprovalId());
    }

    @Test
    public void 기안상신 () {
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
    public void 기안수정 () {
        // given
        Approval.Param param = paramSetup();
        param.setApprovalId(1);
        param.setContent("테스트 내용 수정합니다.");

        // when
        Integer approvalId = approvalService.updateApproval(param);

        // then
        Approval.Search search = searchSetup();
        search.setApprovalId(approvalId);
        Approval result = approvalService.getApproval(search);

        assertThat(result.getContent()).isEqualTo(param.getContent());
    }

    @Test
    public void 기안승인 () {
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
    public void 기안삭제 () {
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
    public void 상신예외_제목없음 () {
        // given
        Approval.Param param = paramSetup();
        param.setTitle(null);

        // when
        ApprovalBadRequestException e = assertThrows(ApprovalBadRequestException.class,
                () -> approvalService.registerApproval(param));

        // then
        assertThat(e.getMessage()).isEqualTo("제목을 입력해주세요.");
    }

    @Test
    public void 상신예외_내용없음 () {
        // given
        Approval.Param param = paramSetup();
        param.setContent(null);

        // when
        ApprovalBadRequestException e = assertThrows(ApprovalBadRequestException.class,
                () -> approvalService.registerApproval(param));

        // then
        assertThat(e.getMessage()).isEqualTo("내용을 입력해주세요.");
    }

    @Test
    public void 상신예외_요청자지정안됨 () {
        // given
        Approval.Param param = paramSetup();
        param.setRequestMemberId(null);

        // when
        ApprovalBadRequestException e = assertThrows(ApprovalBadRequestException.class,
                () -> approvalService.registerApproval(param));

        // then
        assertThat(e.getMessage()).isEqualTo("요청자가 존재하지 않습니다.");
    }

    @Test
    public void 상신예외_승인자지정안됨 () {
        // given
        Approval.Param param = paramSetup();
        param.setApproveMemberId(null);

        // when
        ApprovalBadRequestException e = assertThrows(ApprovalBadRequestException.class,
                () -> approvalService.registerApproval(param));

        // then
        assertThat(e.getMessage()).isEqualTo("승인자가 지정되지 않았습니다.");
    }

    @Test
    public void 상신예외_제목길이 () {
        // given
        Approval.Param param = paramSetup();
        param.setTitle("제목길이를45자이상으로합니다.제목길이를45자이상으로합니다.제목길이를45자이상으로합니다.");

        // when
        ApprovalBadRequestException e = assertThrows(ApprovalBadRequestException.class,
                () -> approvalService.registerApproval(param));

        // then
        assertThat(e.getMessage()).isEqualTo("제목의 길이는 45자를 넘을 수 없습니다.");
    }

    @Test
    public void 직급상태예외_직급낮음 () {
        // given
        Approval.Param param = paramSetup();
        param.setApproveMemberGradeId(4);
        param.setRequestMemberGradeId(1);

        // when
        ApprovalBadRequestException e = assertThrows(ApprovalBadRequestException.class,
                () -> approvalService.registerApproval(param));

        // then
        assertThat(e.getMessage()).isEqualTo("승인자는 요청자보다 직급이 높아야합니다.");
    }

    @Test
    public void 직급상태예외_직급같음 () {
        // given
        Approval.Param param = paramSetup();
        param.setApproveMemberGradeId(1);
        param.setRequestMemberGradeId(1);

        // when
        ApprovalBadRequestException e = assertThrows(ApprovalBadRequestException.class,
                () -> approvalService.registerApproval(param));

        // then
        assertThat(e.getMessage()).isEqualTo("승인자는 요청자와 같은 직급일 수 없습니다.");
    }

    @Test
    public void 요청상태예외_수정 () {
        // given
        Approval.Param param = paramSetup();
        param.setApprovalId(1);
        param.setApprovalStatusCode(ApprovalStatusCode.APPROVE);
        param.setRequestStatusCode(RequestStatusCode.COMPLETE);

        // when
        ApprovalBadRequestException e = assertThrows(ApprovalBadRequestException.class,
                () -> approvalService.updateApproval(param));

        // then
        assertThat(e.getMessage()).isEqualTo("승인 요청 상태의 문서만 수정 가능합니다.");
    }

    @Test
    public void 요청상태예외_삭제 () {
        // given
        Approval.Param param = paramSetup();
        param.setApprovalId(1);
        param.setApprovalStatusCode(ApprovalStatusCode.APPROVE);
        param.setRequestStatusCode(RequestStatusCode.COMPLETE);

        // when
        ApprovalBadRequestException e = assertThrows(ApprovalBadRequestException.class,
                () -> approvalService.deleteApproval(param));

        // then
        assertThat(e.getMessage()).isEqualTo("승인 요청 상태의 문서만 삭제 가능합니다.");
    }

    @Test
    public void 승인상태예외 () {
        // given
        Approval.Param param = paramSetup();
        param.setApprovalId(1);
        param.setRequestStatusCode(RequestStatusCode.COMPLETE);

        // when
        ApprovalBadRequestException e = assertThrows(ApprovalBadRequestException.class,
                () -> approvalService.processApproval(param));

        // then
        assertThat(e.getMessage()).isEqualTo("대기 상태의 문서만 승인 혹은 반려 가능합니다.");
    }

}
