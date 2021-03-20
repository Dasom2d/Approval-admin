package com.dasom.task.kakaoPay.service.approve;

import com.dasom.task.kakaoPay.exception.ApprovalBadRequestException;
import com.dasom.task.kakaoPay.model.approval.Approval;
import com.dasom.task.kakaoPay.model.enumclass.ApprovalStatusCode;
import com.dasom.task.kakaoPay.model.enumclass.RequestStatusCode;
import com.dasom.task.kakaoPay.model.enumclass.code.ApprovalCode;
import com.dasom.task.kakaoPay.repository.approval.ApprovalRepository;
import com.dasom.task.kakaoPay.validation.approval.ApprovalValidator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(SpringRunner.class)
@Slf4j
public class ApprovalServiceTest {

    @Mock
    SqlSessionTemplate sqlSessionTemplate;
    @Mock
    ApprovalRepository approvalRepository = new ApprovalRepository(sqlSessionTemplate);
    @Mock
    ApprovalValidator approvalValidator;
    @InjectMocks
    ApprovalService approvalService = new ApprovalService(approvalRepository, approvalValidator);

    private Approval approvalSetup() {
        return Approval.builder()
                        .approvalId(1)
                        .title("테스트 제목")
                        .content("내용")
                        .approveMemberId(1)
                        .requestMemberId(4)
                        .build();
    }

    private Approval.Search searchSetup() {
        return new Approval.Search(1, "테스트 제목", 2, 4, ApprovalStatusCode.REQUEST, RequestStatusCode.WAIT, 2);
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
                .build();
    }


    @Test
    public void 기안목록조회 () throws Exception {
        // given
        Approval.Search search = searchSetup();
        List<Approval> expectedList = new ArrayList<>();
        Approval expected = approvalSetup();
        expectedList.add(expected);
        given(approvalRepository.getApprovalList(search)).willReturn(expectedList);

        // when
        List<Approval> result = approvalService.getApprovalList(search);

        // then
        assertThat(result.size()).isEqualTo(expectedList.size());
    }

    @Test
    public void 기안단건조회 () throws Exception {
        // given
        Approval.Search search = searchSetup();
        Approval expected = approvalSetup();
        given(approvalRepository.getApproval(search)).willReturn(expected);

        // when
        Approval result = approvalService.getApproval(search);

        // then
        assertThat(result.getApprovalId()).isEqualTo(expected.getApprovalId());
    }

    @Test
    public void 기안상신 () throws Exception {
        // given
        Approval.Param param = paramSetup();
        Approval expected = approvalSetup();
        given(approvalRepository.registerApproval(param)).willReturn(expected.getApprovalId());

        // when
        int resultApprovalId =  approvalService.registerApproval(param);

        // then
        assertThat(resultApprovalId).isEqualTo(expected.getApprovalId());
    }

    @Test
    public void 기안수정 () throws Exception {
        // given
        Approval.Param param = paramSetup();
        param.setApprovalId(1);
        param.setContent("테스트 내용 수정합니다.");

        Approval expected = approvalSetup();
        expected.setContent("테스트 내용 수정합니다.");

        given(approvalRepository.updateApproval(param)).willReturn(expected.getApprovalId());

        // when
        int resultApprovalId = approvalService.updateApproval(param);

        // then
        Approval.Search search = searchSetup();
        search.setApprovalId(resultApprovalId);
        given(approvalRepository.getApproval(search)).willReturn(expected);
        Approval result = approvalService.getApproval(search);

        assertThat(result.getContent()).isEqualTo(param.getContent());
    }

    @Test
    public void 기안승인 () throws Exception {
        // given
        Approval.Param param = paramSetup();
        param.setApprovalId(1);
        param.setApprovalStatusCode(ApprovalStatusCode.APPROVE);

        Approval expected = approvalSetup();
        expected.setApprovalStatusCode(ApprovalStatusCode.APPROVE);

        given(approvalRepository.processApproval(param)).willReturn(expected.getApprovalId());

        // when
        int resultApprovalId = approvalService.processApproval(param);

        // then
        Approval.Search search = searchSetup();
        search.setApprovalId(resultApprovalId);
        given(approvalRepository.getApproval(search)).willReturn(expected);
        Approval result = approvalService.getApproval(search);

        assertThat(result.getApprovalStatusCode()).isEqualTo(param.getApprovalStatusCode());
    }

    @Test
    public void 기안삭제 () throws Exception {
        // given
        Approval.Param param = paramSetup();
        param.setApprovalId(1);

        Approval expected = approvalSetup();

        given(approvalRepository.deleteApproval(param)).willReturn(expected.getApprovalId());

        // when
        int resultApprovalId = approvalService.deleteApproval(param);

        // then
        Approval.Search search = searchSetup();
        search.setApprovalId(resultApprovalId);
        given(approvalRepository.getApproval(search)).willReturn(null);
        Approval result = approvalService.getApproval(search);

        assertNull(result);
    }

    @Test(expected = ApprovalBadRequestException.class)
    public void 상신예외_제목없음 () throws ApprovalBadRequestException {
        // given
        Approval.Param param = paramSetup();
        param.setTitle(null);

        ApprovalBadRequestException e = new ApprovalBadRequestException("제목을 입력해주세요.", ApprovalCode.INVALID_ADD_PARAM.getCode());

        when(approvalRepository.registerApproval(param)).thenThrow(e);

        // when
        approvalService.registerApproval(param);

        // then
        assertThat(e.getMessage()).isEqualTo("제목을 입력해주세요.");
    }

    @Test(expected = ApprovalBadRequestException.class)
    public void 상신예외_내용없음 () throws ApprovalBadRequestException {
        // given
        Approval.Param param = paramSetup();
        param.setContent(null);

        ApprovalBadRequestException e = new ApprovalBadRequestException("내용을 입력해주세요.", ApprovalCode.INVALID_ADD_PARAM.getCode());

        when(approvalRepository.registerApproval(param)).thenThrow(e);

        // when
        approvalService.registerApproval(param);;

        // then
        assertThat(e.getMessage()).isEqualTo("내용을 입력해주세요.");
    }

    @Test(expected = ApprovalBadRequestException.class)
    public void 상신예외_요청자지정안됨 () throws ApprovalBadRequestException {
        // given
        Approval.Param param = paramSetup();
        param.setRequestMemberId(null);

        ApprovalBadRequestException e = new ApprovalBadRequestException("요청자가 존재하지 않습니다.", ApprovalCode.INVALID_ADD_PARAM.getCode());

        when(approvalRepository.registerApproval(param)).thenThrow(e);

        // then
        assertThat(e.getMessage()).isEqualTo("요청자가 존재하지 않습니다.");
    }

    @Test(expected = ApprovalBadRequestException.class)
    public void 상신예외_승인자지정안됨 () throws ApprovalBadRequestException {
        // given
        Approval.Param param = paramSetup();
        param.setApproveMemberId(null);

        ApprovalBadRequestException e = new ApprovalBadRequestException("승인자가 존재하지 않습니다.", ApprovalCode.INVALID_ADD_PARAM.getCode());

        // then
        when(approvalRepository.registerApproval(param)).thenThrow(e);

        // then
        assertThat(e.getMessage()).isEqualTo("승인자가 존재하지 않습니다.");
    }

    @Test(expected = ApprovalBadRequestException.class)
    public void 상신예외_제목길이() throws ApprovalBadRequestException {
        // given
        Approval.Param param = paramSetup();
        param.setTitle("제목길이를45자이상으로합니다.제목길이를45자이상으로합니다.제목길이를45자이상으로합니다.");

        ApprovalBadRequestException e = new ApprovalBadRequestException("제목의 길이는 45자를 넘을 수 없습니다.", ApprovalCode.INVALID_ADD_PARAM.getCode());

        // then
        when(approvalRepository.registerApproval(param)).thenThrow(e);

        // then
        assertThat(e.getMessage()).isEqualTo("제목의 길이는 45자를 넘을 수 없습니다.");
    }

    @Test(expected = ApprovalBadRequestException.class)
    public void 직급상태예외_직급낮음() throws ApprovalBadRequestException {
        // given
        Approval.Param param = paramSetup();
        param.setApproveMemberGradeId(4);
        param.setRequestMemberGradeId(1);

        ApprovalBadRequestException e = new ApprovalBadRequestException("승인자는 요청자보다 직급이 높아야합니다.", ApprovalCode.INVALID_ADD_PARAM.getCode());

        // then
        when(approvalRepository.registerApproval(param)).thenThrow(e);

        // then
        assertThat(e.getMessage()).isEqualTo("승인자는 요청자보다 직급이 높아야합니다.");
    }

    @Test(expected = ApprovalBadRequestException.class)
    public void 직급상태예외_직급같음() throws ApprovalBadRequestException {
        // given
        Approval.Param param = paramSetup();
        param.setApproveMemberGradeId(1);
        param.setRequestMemberGradeId(1);

        ApprovalBadRequestException e = new ApprovalBadRequestException("승인자는 요청자와 같은 직급일 수 없습니다.", ApprovalCode.INVALID_ADD_PARAM.getCode());

        // then
        when(approvalRepository.registerApproval(param)).thenThrow(e);

        // then
        assertThat(e.getMessage()).isEqualTo("승인자는 요청자와 같은 직급일 수 없습니다.");
    }

    @Test(expected = ApprovalBadRequestException.class)
    public void 요청상태예외_수정 () throws ApprovalBadRequestException {
        // given
        Approval.Param param = paramSetup();
        param.setApprovalId(1);
        param.setApprovalStatusCode(ApprovalStatusCode.APPROVE);
        param.setRequestStatusCode(RequestStatusCode.COMPLETE);

        ApprovalBadRequestException e = new ApprovalBadRequestException("승인 요청 상태의 문서만 수정 가능합니다.", ApprovalCode.INVALID_ADD_PARAM.getCode());

        // then
        when(approvalRepository.updateApproval(param)).thenThrow(e);

        // then
        assertThat(e.getMessage()).isEqualTo("승인 요청 상태의 문서만 수정 가능합니다.");
    }

    @Test(expected = ApprovalBadRequestException.class)
    public void 요청상태예외_삭제 () throws ApprovalBadRequestException {
        // given
        Approval.Param param = paramSetup();
        param.setApprovalId(1);
        param.setApprovalStatusCode(ApprovalStatusCode.APPROVE);
        param.setRequestStatusCode(RequestStatusCode.COMPLETE);

        ApprovalBadRequestException e = new ApprovalBadRequestException("승인 요청 상태의 문서만 삭제 가능합니다.", ApprovalCode.INVALID_ADD_PARAM.getCode());

        // then
        when(approvalRepository.deleteApproval(param)).thenThrow(e);

        // then
        assertThat(e.getMessage()).isEqualTo("승인 요청 상태의 문서만 삭제 가능합니다.");
    }

    @Test
    public void 승인상태예외 () {
        // given
        Approval.Param param = paramSetup();
        param.setApprovalId(1);
        param.setRequestStatusCode(RequestStatusCode.COMPLETE);

        ApprovalBadRequestException e = new ApprovalBadRequestException("대기 상태의 문서만 승인 혹은 반려 가능합니다.", ApprovalCode.INVALID_ADD_PARAM.getCode());

        // then
        when(approvalRepository.processApproval(param)).thenThrow(e);

        // then
        assertThat(e.getMessage()).isEqualTo("대기 상태의 문서만 승인 혹은 반려 가능합니다.");
    }

}
