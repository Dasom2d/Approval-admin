package com.dasom.task.kakaoPay.repository.member;

import com.dasom.task.kakaoPay.model.member.Member;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class memberRepositoryTest {

    MemberRepository memberRepository;
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @BeforeEach
    public void beforeEach () {
        memberRepository = new MemberRepository(sqlSessionTemplate);
    }

    @Test
    public void 멤버조회 () {
        // given
        Integer memberId = 1;

        // when
        Member result = memberRepository.getMemberInfo(memberId);

        // then
        assertThat(result.getMemberId()).isEqualTo(memberId);
    }

    @Test
    public void 승인가능멤버리스트조회 (){
        // given
        Integer memberId = 2;

        // when
        List<Member> memberList = memberRepository.getMemberList(memberId);

        // then
        Integer memberGradeId = memberRepository.getMemberInfo(2).getGradeId();
        assertThat(memberList.get(0).getGradeId()).isGreaterThan(memberGradeId);
    }
}
