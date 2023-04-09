package com.shopleech.publicapi.domain;

import com.shopleech.base.config.type.BarcodeTypeCode;
import com.shopleech.base.config.type.ProductTypeCode;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ahto Jalak
 * @since 04.02.2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private ProductTypeCode productTypeCode;
    private String barcode;
    private BarcodeTypeCode barcodeTypeCode;
    private String name;
    private String description;

    private Timestamp validFrom;
    private Timestamp validTo;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;

    @OneToMany(mappedBy="product")
    private Set<Watchlist> watchlists = new HashSet<>();;

    @OneToMany(mappedBy="product")
    private Set<Alarm> alarms = new HashSet<>();

    @OneToMany(mappedBy="product")
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy="product")
    private Set<Offer> offers = new HashSet<>();

    @OneToMany(mappedBy="product")
    private Set<Metric> metrics = new HashSet<>();
}
