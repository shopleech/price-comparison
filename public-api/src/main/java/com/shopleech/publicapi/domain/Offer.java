package com.shopleech.publicapi.domain;

import com.shopleech.base.config.type.BarcodeTypeCode;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
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
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = true)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = true)
    private Shop shop;

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

    @OneToMany(mappedBy="offer")
    private Set<Feature> features = new HashSet<>();

    @OneToMany(mappedBy="offer")
    private Set<Price> prices = new HashSet<>();

    @OneToMany(mappedBy="offer")
    private Set<Metric> metrics = new HashSet<>();
}
