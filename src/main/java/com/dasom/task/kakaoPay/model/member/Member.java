package com.dasom.task.kakaoPay.model.member;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Member {

    private Integer memberId;
    private String name;
    private Integer gradeId;

    public Member(Integer memberId) {
        this.memberId = memberId;
    }

    public Member(Integer memberId, Integer gradeId) {
        this.memberId = memberId;
        this.gradeId = gradeId;
    }

    public Member(Integer memberId, String name, Integer gradeId) {
        this.memberId = memberId;
        this.name = name;
        this.gradeId = gradeId;
    }
}
