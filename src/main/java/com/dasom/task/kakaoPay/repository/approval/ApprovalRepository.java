package com.dasom.task.kakaoPay.repository.approval;

import com.dasom.task.kakaoPay.model.approval.Approval;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApprovalRepository {

    private SqlSessionTemplate sqlSessionTemplate;

    public ApprovalRepository(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }


    private static final String PREFIX = ApprovalRepository.class.getCanonicalName() + ".";


    public Approval.ApprovalDocument getApproval(Approval.Search search) {
        return sqlSessionTemplate.selectOne(PREFIX + "getApproval", search);
    }

    public List<Approval.ApprovalDocument> getApprovalList(Approval.Search search) {
        return sqlSessionTemplate.selectList(PREFIX + "getApprovalList", search);
    }

    public Integer registerApproval(Approval.AddParam addParam) {
        return sqlSessionTemplate.insert(PREFIX + "insertApproval", addParam);
    }

    public Integer updateApproval(Approval.AddParam addParam) {
        return sqlSessionTemplate.insert(PREFIX + "updateApproval", addParam);
    }

    public Integer deleteApproval(Approval.AddParam addParam) {
        return sqlSessionTemplate.delete(PREFIX + "deleteApproval", addParam);
    }

}
