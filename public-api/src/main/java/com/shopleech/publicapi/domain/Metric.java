package com.shopleech.publicapi.domain;

import com.shopleech.base.config.type.MetricTypeCode;
import com.shopleech.base.domain.BaseDomainEntityMetaId;
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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "_metric")
public class Metric extends BaseDomainEntityMetaId {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private AccountProduct accountProduct;

    @ManyToOne
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
