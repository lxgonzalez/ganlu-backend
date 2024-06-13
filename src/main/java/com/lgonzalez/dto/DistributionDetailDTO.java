package com.lgonzalez.dto;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistributionDetailDTO {
    private long idDistributionDetail;
    private String description;
    private Double subtotal;
    private CategoryDTO category;

}
