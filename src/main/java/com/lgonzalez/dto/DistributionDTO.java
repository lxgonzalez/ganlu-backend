package com.lgonzalez.dto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistributionDTO {
    private long idDistribution;
    private String name;
    private String description;
    private Integer percentage;
    private Double total;
    private Double totalFinal;
    @JsonBackReference
    private MonthlyDTO monthlyFinance;
    private List<DistributionDetailDTO> distributionDetails;
}
