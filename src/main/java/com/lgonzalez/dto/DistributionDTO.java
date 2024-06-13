package com.lgonzalez.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistributionDTO {
    private String name;
    private String description;
    private Integer percentage;
    private Double total;
    private MonthlyDTO monthlyDTO;
}
