package com.dasom.task.kakaoPay.repository.approval;

import com.dasom.task.kakaoPay.model.approval.Approval;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApprovalRepository {

    private final SqlSessionTemplate sqlSessionTemplate;

    public ApprovalRepository(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    private static final String PREFIX = ApprovalRepository.class.getCanonicalName() + ".";

    public Approval getApproval(Approval.Search search) {
        return sqlSessionTemplate.selectOne(PREFIX + "getApproval", search);
    }

    public List<Approval> getApprovalList(Approval.Search search) {
        return sqlSessionTemplate.selectList(PREFIX + "getApprovalList", search);
    }

    public Integer registerApproval(Approval.Param param) {
        return sqlSessionTemplate.insert(PREFIX + "insertApproval", param);
    }

    public Integer updateApproval(Approval.Param param) {
        return sqlSessionTemplate.insert(PREFIX + "updateApproval", param);
    }

    public Integer processApproval(Approval.Param param) {
        return sqlSessionTemplate.insert(PREFIX + "processApproval", param);
    }

    public Integer deleteApproval(Approval.Param param) {
        return sqlSessionTemplate.delete(PREFIX + "deleteApproval", param);
    }

}
