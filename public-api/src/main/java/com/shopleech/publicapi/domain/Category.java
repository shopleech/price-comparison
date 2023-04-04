package com.shopleech.publicapi.domain;

import com.shopleech.base.config.type.CategoryTypeCode;
import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "parent_category_id", nullable = true)
    private Category parentCategory;

    private String name;
    private CategoryTypeCode categoryTypeCode;

    private Timestamp validFrom;
    private Timestamp validTo;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;

    @OneToMany(mappedBy="category")
    private Set<Product> products = new HashSet<>();

    @OneToMany(mappedBy="parentCategory")
    private Set<Category> categories = new HashSet<>();
}
