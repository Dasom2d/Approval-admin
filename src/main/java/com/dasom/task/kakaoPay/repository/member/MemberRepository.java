package com.dasom.task.kakaoPay.repository.member;

import com.dasom.task.kakaoPay.model.member.Member;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class MemberRepository {
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    private static final String PREFIX = MemberRepository.class.getCanonicalName() + ".";

    public List<Member> getMemberList(){
        return sqlSessionTemplate.selectList(PREFIX + "getMemberList");
    }

}
