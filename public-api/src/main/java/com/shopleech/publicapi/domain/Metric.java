package com.shopleech.publicapi.domain;

import com.shopleech.base.config.type.MetricTypeCode;
import com.shopleech.base.domain.BaseDomainEntityMetaId;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "metric")
public class Metric extends BaseDomainEntityMetaId {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = true)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = true)
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;

    private MetricTypeCode metricTypeCode;
    private Double quantity;

    private Timestamp validFrom;
    private Timestamp validTo;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
}
