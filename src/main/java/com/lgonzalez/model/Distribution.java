package com.lgonzalez.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Distribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDistribution;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 100)
    private String description;
    @Column(nullable = false)
    private Integer percentage;
    @Column(nullable = false)
    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_monthly_finance", nullable = false, foreignKey = @ForeignKey(name = "FK_DISTRIBUTION_MONTHLY"))
    private MonthlyFinance monthlyFinance;

    public Distribution(String name, String description, Integer percentage, Double total, MonthlyFinance monthlyFinance){
        this.name = name;
        this.description = description;
        this.percentage = percentage;
        this.total = total;
        this.monthlyFinance = monthlyFinance;
    }
}
