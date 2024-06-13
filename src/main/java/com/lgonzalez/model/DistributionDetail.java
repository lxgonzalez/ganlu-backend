package com.lgonzalez.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DistributionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDistributionDetail;
    @Column(nullable = false, length = 150)
    private String description;
    @Column(nullable = false)
    private Double subtotal;
    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false, foreignKey = @ForeignKey(name = "FK_DETAIL_CATEGORY"))
    private Category category;
}
