package com.dasom.task.kakaoPay.service.member;

import com.dasom.task.kakaoPay.model.member.Member;
import com.dasom.task.kakaoPay.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MemberServiceIntegrateTest {
    MemberService memberService;
    MemberRepository memberRepository;
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemberRepository(sqlSessionTemplate);
        memberService = new MemberService(memberRepository);
    }

    @Test
    public void 승인가능멤버리스트조회() {
        // given
        Integer memberId = 2;

        // when
        List<Member> memberList = memberService.getMemberList(memberId);

        // then
        Integer memberGradeId = memberService.getMemberInfo(memberId).getGradeId();

        assertThat(memberList.get(0).getGradeId()).isLessThan(memberGradeId);
        assertNotNull(memberList);

    }
}
