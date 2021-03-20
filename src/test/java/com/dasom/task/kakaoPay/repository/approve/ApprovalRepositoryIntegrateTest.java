package com.dasom.task.kakaoPay.repository.approve;

import com.dasom.task.kakaoPay.model.approval.Approval;
import com.dasom.task.kakaoPay.model.enumclass.ApprovalStatusCode;
import com.dasom.task.kakaoPay.model.enumclass.RequestStatusCode;
import com.dasom.task.kakaoPay.repository.approval.ApprovalRepository;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class ApprovalRepositoryIntegrateTest {

    ApprovalRepository approvalRepository;
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @BeforeEach
    public void beforeEach () {
        approvalRepository = new ApprovalRepository(sqlSessionTemplate);
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


    @Test
    public void 목록조회테스트 () {
        Approval.Search search = new Approval.Search();
        search.setTitle("휴가신청서");

        // when
        List<Approval> results = approvalRepository.getApprovalList(search);

        // then
        assertThat(results.get(0).getTitle()).isEqualTo(search.getTitle());
    }


    @Test
    public void 단건조회테스트 () {
        // given
        Approval.Search search = searchSetup();
        search.setApprovalId(1);

        // when
        Approval result = approvalRepository.getApproval(search);

        // then
        assertThat(result.getApprovalId()).isEqualTo(search.getApprovalId());
    }

    @Test
    public void 등록테스트 () {
        // given
        Approval.Param param = paramSetup();
        param.setTitle("테스트 신청서");
        param.setContent("테스트 신청합니다.");
        param.setApproveMemberId(1);
        param.setRequestMemberId(4);
        param.setApprovalStatusCode(ApprovalStatusCode.REQUEST);
        param.setRequestStatusCode(RequestStatusCode.WAIT);
        param.setRegisterMemberId(4);

        // when
        approvalRepository.registerApproval(param);

        // then
        Approval.Search search = searchSetup();
        search.setApprovalId(param.getApprovalId());
        Approval result = approvalRepository.getApproval(search);
        assertThat(result.getApprovalId()).isEqualTo(search.getApprovalId());
   }

   @Test
    public void 수정테스트 () {
        // given
       Approval.Param param = paramSetup();
       param.setApprovalId(1);
       param.setContent("테스트 내용 수정합니다.");

       // when
       approvalRepository.updateApproval(param);

       // then
       Approval.Search search = searchSetup();
       search.setApprovalId(1);
       Approval result = approvalRepository.getApproval(search);
       assertThat(result.getContent()).isEqualTo(param.getContent());
   }

    @Test
    public void 승인테스트 () {
        // given
        Approval.Param param = paramSetup();
        param.setApprovalId(1);
        param.setApprovalStatusCode(ApprovalStatusCode.APPROVE);
        param.setRequestStatusCode(RequestStatusCode.COMPLETE);

        // when
        approvalRepository.processApproval(param);

        // then
        Approval.Search search = searchSetup();
        search.setApprovalId(1);
        ApprovalStatusCode result = approvalRepository.getApproval(search).getApprovalStatusCode();
        assertThat(result).isEqualTo(param.getApprovalStatusCode());
    }

   @Test
    public void 삭제테스트() {
        // given
       Approval.Param param = paramSetup();
       param.setApprovalId(1);

       // when
       approvalRepository.deleteApproval(param);

       // then
       Approval.Search search = searchSetup();
       search.setApprovalId(param.getApprovalId());
       Approval result = approvalRepository.getApproval(search);
       assertNull(result);
   }

}
