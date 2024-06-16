package com.lgonzalez.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCategory;
    @Column(nullable = false, length = 50)
    private String categoryName;
    @Column(nullable = true, length = 250)
    private String logo;
}
