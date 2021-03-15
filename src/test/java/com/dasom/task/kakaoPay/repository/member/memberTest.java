package com.dasom.task.kakaoPay.repository.member;

import com.dasom.task.kakaoPay.model.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class memberTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void getMemberList(){
       List<Member> memberList = memberRepository.getMemberList();
       assertThat(memberList.size()).isEqualTo(4);

    }
}
