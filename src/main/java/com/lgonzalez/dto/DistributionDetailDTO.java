package com.lgonzalez.dto;
import com.lgonzalez.model.Distribution;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistributionDetailDTO {
    private String description;
    private Double subtotal;
    private CategoryDTO category;
}
