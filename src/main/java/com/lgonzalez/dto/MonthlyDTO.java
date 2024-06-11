package com.lgonzalez.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyFinanceDTO {
    private String month;
    private String year;
    private Double salary;
    private MemberDTO member;
}
