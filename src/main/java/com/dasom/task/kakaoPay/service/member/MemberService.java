package com.dasom.task.kakaoPay.service.member;

import com.dasom.task.kakaoPay.model.member.Member;
import com.dasom.task.kakaoPay.repository.member.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member getMemberInfo(Integer memberId) {
        return memberRepository.getMemberInfo(memberId);
    }

    public List<Member> getMemberList(Integer memberId) {
        return memberRepository.getMemberList(memberId);
    }
}
