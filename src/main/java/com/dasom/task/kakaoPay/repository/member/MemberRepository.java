package com.dasom.task.kakaoPay.repository.member;

import com.dasom.task.kakaoPay.model.member.Member;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    private SqlSessionTemplate sqlSessionTemplate;

    public MemberRepository(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    private static final String PREFIX = MemberRepository.class.getCanonicalName() + ".";

    public Member getMemberInfo(Integer memberId){
        return sqlSessionTemplate.selectOne(PREFIX + "getMemberInfo", memberId);
    }

    public List<Member> getMemberList(Integer memberId){
        return sqlSessionTemplate.selectList(PREFIX + "getMemberList", memberId);
    }

}
