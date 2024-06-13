package com.lgonzalez.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MonthlyFinance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMonthlyFinance;
    @Column(nullable = false)
    private Integer monthFinance;
    @Column(nullable = false)
    private Integer yearFinance;
    private Double salary;
    private Double Total;
    @ManyToOne
    @JoinColumn(name = "id_member", nullable = false, foreignKey = @ForeignKey(name = "FK_MONTHLY_MEMBER"))
    private Member member;
    @OneToMany(mappedBy = "monthlyFinance", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Distribution> distributions;

}
