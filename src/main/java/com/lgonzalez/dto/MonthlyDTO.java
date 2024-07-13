package com.lgonzalez.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyDTO {
    private Integer monthFinance;
    private Integer yearFinance;
    private Double salary;
    private Double total;
    private Long idMember;
    private List<DistributionDTO> distributions;
}
