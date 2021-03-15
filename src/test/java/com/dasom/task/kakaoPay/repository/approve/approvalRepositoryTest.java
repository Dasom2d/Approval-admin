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

    Approval.AddParam addParam = new Approval.AddParam();
    Approval.Search search = new Approval.Search();

    @Test
    public void 단건조회테스트 () {
        // given
        search.setApprovalId(1);

        // when
        Approval.ApprovalDocument result = approvalRepository.getApproval(search);

        // then
        assertThat(result.getApprovalId()).isEqualTo(search.getApprovalId());
    }

    @Test
    public void 등록테스트 () {
        // given
        addParam.setTitle("테스트 신청서");
        addParam.setContent("테스트 신청합니다.");
        addParam.setApproveMemberId(1);
        addParam.setRequestMemberId(4);
        addParam.setApproveStatusCode(ApprovalStatusCode.REQUEST);
        addParam.setRequestStatusCode(RequestStatusCode.WAIT);
        addParam.setRegisterDate(now());
        addParam.setRegisterMemberId(4);

        // when
        approvalRepository.registerApproval(addParam);

        // then
        search.setApprovalId(addParam.getApprovalId());
        Approval.ApprovalDocument result = approvalRepository.getApproval(search);
        assertThat(result.getApprovalId()).isEqualTo(search.getApprovalId());
   }

   @Test
   public void 목록조회테스트 () {
        // given
       search.setApprovalId(1);

       // when
       List<Approval.ApprovalDocument> result = approvalRepository.getApprovalList(search);

       // then
       assertThat(result.get(0).getApprovalId()).isEqualTo(search.getApprovalId());
   }


   @Test
    public void 수정테스트 () {
        // given
       addParam.setApprovalId(1);
       addParam.setApproveStatusCode(ApprovalStatusCode.APPROVE);
       addParam.setRequestStatusCode(RequestStatusCode.COMPLETE);
       search.setApprovalId(1);

       // when
       approvalRepository.updateApproval(addParam);

       // then
       Approval.ApprovalDocument result = approvalRepository.getApproval(search);
       assertThat(result.getApprovalId()).isEqualTo(addParam.getApprovalId());

   }

   @Test
    public void deleteApproval() {
        // given
       addParam.setApprovalId(1);

       // when
       approvalRepository.deleteApproval(addParam);

       // then
       search.setApprovalId(addParam.getApprovalId());
       Approval.ApprovalDocument result = approvalRepository.getApproval(search);
       assertNull(result);
   }

}
