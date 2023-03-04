package com.shopleech.publicapi.domain;

import com.shopleech.base.config.BarcodeTypeCode;
import com.shopleech.base.config.ProductTypeCode;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
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
@Table(name = "_product")
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
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

    @OneToMany
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Set<Watchlist> watchlists;

    @OneToMany
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Set<Alarm> alarms;

    @OneToMany
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Set<AccountProduct> accountProducts;
}
