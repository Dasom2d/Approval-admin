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
import static org.assertj.core.util.DateUtil.now;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class ApprovalRepositoryTest {

    ApprovalRepository approvalRepository;
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @BeforeEach
    public void beforeEach () {
        approvalRepository = new ApprovalRepository(sqlSessionTemplate);
    }

    @Test
    public void 목록조회테스트 () {
        // given
        Approval.Search search = new Approval.Search();
        search.setApprovalId(1);
        search.setTitle("휴가신청서");
        search.setApproveMemberId(2);
        search.setRequestMemberId(4);
        search.setRequestStatusCode(RequestStatusCode.WAIT);
        search.setApprovalStatusCode(ApprovalStatusCode.REQUEST);

        // when
        List<Approval> result = approvalRepository.getApprovalList(search);

        // then
        assertThat(result.get(0).getApprovalId()).isEqualTo(search.getApprovalId());
    }


    @Test
    public void 단건조회테스트 () {
        // given
        Approval.Search search = new Approval.Search();
        search.setApprovalId(1);

        // when
        Approval result = approvalRepository.getApproval(search);

        // then
        assertThat(result.getApprovalId()).isEqualTo(search.getApprovalId());
    }

    @Test
    public void 등록테스트 () {
        // given
        Approval.Param param = new Approval.Param();
        param.setTitle("테스트 신청서");
        param.setContent("테스트 신청합니다.");
        param.setApproveMemberId(1);
        param.setRequestMemberId(4);
        param.setApprovalStatusCode(ApprovalStatusCode.REQUEST);
        param.setRequestStatusCode(RequestStatusCode.WAIT);
        param.setRegisterDate(now());
        param.setRegisterMemberId(4);

        // when
        approvalRepository.registerApproval(param);

        // then
        Approval.Search search = new Approval.Search();
        search.setApprovalId(param.getApprovalId());
        Approval result = approvalRepository.getApproval(search);
        assertThat(result.getApprovalId()).isEqualTo(search.getApprovalId());
   }

   @Test
    public void 수정테스트 () {
        // given
       Approval.Param param = new Approval.Param();
       param.setApprovalId(1);
       param.setContent("테스트 내용 수정합니다.");

       // when
       approvalRepository.updateApproval(param);

       // then
       Approval.Search search = new Approval.Search();
       search.setApprovalId(1);
       Approval result = approvalRepository.getApproval(search);
       assertThat(result.getContent()).isEqualTo(param.getContent());

   }

   @Test
    public void deleteApproval() {
        // given
       Approval.Param param = new Approval.Param();
       param.setApprovalId(1);

       // when
       approvalRepository.deleteApproval(param);

       // then
       Approval.Search search = new Approval.Search();
       search.setApprovalId(param.getApprovalId());
       Approval result = approvalRepository.getApproval(search);
       assertNull(result);
   }

}
