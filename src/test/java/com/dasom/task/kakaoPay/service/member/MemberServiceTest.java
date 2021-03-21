package com.dasom.task.kakaoPay.service.member;

import com.dasom.task.kakaoPay.model.member.Member;
import com.dasom.task.kakaoPay.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(SpringRunner.class)
@Slf4j
public class MemberServiceTest {

    @Mock
    SqlSessionTemplate sqlSessionTemplate;
    @Mock
    MemberRepository memberRepository = new MemberRepository(sqlSessionTemplate);
    @InjectMocks
    MemberService memberService = new MemberService(memberRepository);


    @Test
    public void 승인가능멤버리스트조회() throws Exception {
        // given
        Member target = new Member(2, 2);
        Member expected = new Member(1, 1);
        List<Member> expectedMemberList = new ArrayList<>();
        expectedMemberList.add(expected);
        given(memberRepository.getMemberList(target.getMemberId())).willReturn(expectedMemberList);

        // when
        List<Member> result = memberService.getMemberList(target.getMemberId());

        // then
        assertThat(result.get(0).getGradeId()).isLessThan(target.getMemberId());
        assertNotNull(result);

    }
}
