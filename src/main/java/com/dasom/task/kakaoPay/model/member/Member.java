package com.dasom.task.kakaoPay.model.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private Integer memberId;
    private String name;
    private Integer gradeId;
    private String gradeName;

    public Member(Integer memberId, Integer gradeId) {
        this.memberId = memberId;
        this.gradeId = gradeId;
    }

}
