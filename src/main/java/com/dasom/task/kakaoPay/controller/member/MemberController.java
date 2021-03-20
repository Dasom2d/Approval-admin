package com.dasom.task.kakaoPay.controller.member;

import com.dasom.task.kakaoPay.model.member.Member;
import com.dasom.task.kakaoPay.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 멤버 목록 조회
     * @param memberId
     * @return List<Member>
     */

    @GetMapping("/getMemberList")
    @ResponseBody
    public List<Member> getMemberList(@RequestParam(required = false) Integer memberId) {
        List<Member> m = memberService.getMemberList(memberId);
        return m;
    }
}
