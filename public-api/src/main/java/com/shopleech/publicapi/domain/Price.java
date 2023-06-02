package com.shopleech.publicapi.domain;

import com.shopleech.base.config.type.CurrencyTypeCode;
import com.shopleech.base.config.type.PriceTypeCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "price")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_generator")
    @SequenceGenerator(name = "price_generator", sequenceName = "price_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    private PriceTypeCode priceTypeCode;
    private Double quantity;
    private Double amount;
    private CurrencyTypeCode currency;

    private Timestamp validFrom;
    private Timestamp validTo;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
}
