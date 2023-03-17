package com.shopleech.publicapi.domain;

import com.shopleech.base.config.type.BarcodeTypeCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

/**
 * @author Ahto Jalak
 * @since 05.02.2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "_account_product")
public class AccountProduct {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Account account;

    @ManyToOne
    private Product product;

    private String barcode;
    private BarcodeTypeCode barcodeTypeCode;
    private String name;
    private String description;
    private String url;

    private Timestamp validFrom;
    private Timestamp validTo;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;

    @OneToMany
    @JoinColumn(name = "account_product_id", referencedColumnName = "id")
    private Set<Review> reviews;

    @OneToMany
    @JoinColumn(name = "account_product_id", referencedColumnName = "id")
    private Set<Feature> features;

    @OneToMany
    @JoinColumn(name = "account_product_id", referencedColumnName = "id")
    private Set<Price> prices;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
