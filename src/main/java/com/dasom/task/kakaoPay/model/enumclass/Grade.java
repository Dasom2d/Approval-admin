package com.dasom.task.kakaoPay.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Grade {

    CHAIRMAN(1, "사장", "Chairman"),
    MANAGER(2, "과장", "Manager"),
    CHIF(3, "대리", "Chif"),
    STAFF(4, "사원", "Staff");

    private Integer gradeId;
    private String gradeNameKr;
    private String gradeNameEn;

}
