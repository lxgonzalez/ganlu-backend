package com.lgonzalez.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyFinance {

    private long idMonthlyFinance;
    private Date date;
    private Double salary;
    private Double Total;
    private MemberDTO member;
    private List<Distribution> distributions;

}
