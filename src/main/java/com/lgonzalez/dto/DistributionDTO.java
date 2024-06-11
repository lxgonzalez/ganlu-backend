package com.lgonzalez.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Distribution {
    private long idDistribution;
    private String name;
    private String description;
    private Integer percentage;
    private Double total;
    private MonthlyFinanceDTO monthlyFinance;
}
