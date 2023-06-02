package com.shopleech.publicapi.domain;

import com.shopleech.base.config.type.CategoryTypeCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
    @SequenceGenerator(name = "category_generator", sequenceName = "category_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "parent_category_id", nullable = true)
    private Category parentCategory;

    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "category_type_code")
    private CategoryTypeCode categoryTypeCode;

    private Timestamp validFrom;
    private Timestamp validTo;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;

    @OneToMany(mappedBy = "category")
    private Set<Product> products = new HashSet<>();

    @OneToMany(mappedBy = "parentCategory")
    private Set<Category> categories = new HashSet<>();
}
