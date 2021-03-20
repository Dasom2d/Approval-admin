package com.dasom.task.kakaoPay.controller.approval;

import com.dasom.task.kakaoPay.model.approval.Approval;
import com.dasom.task.kakaoPay.model.approval.ApprovalResponse;
import com.dasom.task.kakaoPay.model.enumclass.ApprovalStatusCode;
import com.dasom.task.kakaoPay.model.enumclass.RequestStatusCode;
import com.dasom.task.kakaoPay.repository.approval.ApprovalRepository;
import com.dasom.task.kakaoPay.service.approve.ApprovalService;
import com.dasom.task.kakaoPay.validation.approval.ApprovalValidator;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApprovalControllerTest {

    @Mock
    SqlSessionTemplate sqlSessionTemplate;
    @Mock
    ApprovalRepository approvalRepository = new ApprovalRepository(sqlSessionTemplate);
    @Mock
    ApprovalValidator approvalValidator;
    @Mock
    ApprovalService approvalService = new ApprovalService(approvalRepository, approvalValidator);

    @InjectMocks
    ApprovalController approvalController;

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
                .build();
    }

    public void getApproval() throws Exception {

        // given
        Approval.Search search = searchSetup();
        Approval expected = approvalSetup();
        given(approvalService.getApproval(search)).willReturn(expected);

        // when
        ResponseEntity<ApprovalResponse<Approval>> result = approvalController.getApproval(search);

    }
}
