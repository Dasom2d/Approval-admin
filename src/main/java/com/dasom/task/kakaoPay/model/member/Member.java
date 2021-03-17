package com.dasom.task.kakaoPay.model.member;

import lombok.Data;

@Data
public class Member {

    private Integer memberId;
    private String name;
    private Integer gradeId;

    public Member(Integer memberId, Integer gradeId) {
        this.memberId = memberId;
        this.gradeId = gradeId;
    }

}
