package com.dasom.task.kakaoPay.repository.approve;

import com.dasom.task.kakaoPay.model.approval.Approval;
import com.dasom.task.kakaoPay.model.enumclass.ApprovalStatusCode;
import com.dasom.task.kakaoPay.model.enumclass.RequestStatusCode;
import com.dasom.task.kakaoPay.repository.approval.ApprovalRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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
public class approvalRepositoryTest {

    @Autowired
    ApprovalRepository approvalRepository;


    @Test
    public void 단건조회테스트 () {
        // given
        Approval.Search search = new Approval.Search();
        search.setApprovalId(1);

        // when
        Approval.ApprovalDocument result = approvalRepository.getApproval(search);

        // then
        assertThat(result.getApprovalId()).isEqualTo(search.getApprovalId());
    }

    @Test
    public void 등록테스트 () {
        // given
        Approval.AddParam addParam = new Approval.AddParam();
        addParam.setTitle("테스트 신청서");
        addParam.setContent("테스트 신청합니다.");
        addParam.setApproveMemberId(1);
        addParam.setRequestMemberId(4);
        addParam.setApprovalStatusCode(ApprovalStatusCode.REQUEST);
        addParam.setRequestStatusCode(RequestStatusCode.WAIT);
        addParam.setRegisterDate(now());
        addParam.setRegisterMemberId(4);

        // when
        approvalRepository.registerApproval(addParam);

        // then
        Approval.Search search = new Approval.Search();
        search.setApprovalId(addParam.getApprovalId());
        Approval.ApprovalDocument result = approvalRepository.getApproval(search);
        assertThat(result.getApprovalId()).isEqualTo(search.getApprovalId());
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
       List<Approval.ApprovalDocument> result = approvalRepository.getApprovalList(search);

       // then
       assertThat(result.get(0).getApprovalId()).isEqualTo(search.getApprovalId());
   }


   @Test
    public void 수정테스트 () {
        // given
       Approval.AddParam addParam = new Approval.AddParam();
       addParam.setApprovalId(1);
       addParam.setApprovalStatusCode(ApprovalStatusCode.APPROVE);
       addParam.setRequestStatusCode(RequestStatusCode.COMPLETE);
       Approval.Search search = new Approval.Search();
       search.setApprovalId(1);

       // when
       approvalRepository.updateApproval(addParam);

       // then
       Approval.ApprovalDocument result = approvalRepository.getApproval(search);
       assertThat(result.getApproveStatusCode()).isEqualTo(addParam.getApprovalStatusCode());

   }

   @Test
    public void deleteApproval() {
        // given
       Approval.AddParam addParam = new Approval.AddParam();
       addParam.setApprovalId(1);

       // when
       approvalRepository.deleteApproval(addParam);

       // then
       Approval.Search search = new Approval.Search();
       search.setApprovalId(addParam.getApprovalId());
       Approval.ApprovalDocument result = approvalRepository.getApproval(search);
       assertNull(result);
   }

}
