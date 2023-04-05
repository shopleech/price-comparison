package com.shopleech.publicapi.dto.v1;

import com.shopleech.base.config.type.MetricTypeCode;
import com.shopleech.publicapi.domain.Category;
import com.shopleech.publicapi.domain.Offer;
import com.shopleech.publicapi.domain.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 05.04.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetricDTO {
    private Integer id;

    private Product product;

    private Offer offer;

    private Category category;

    private MetricTypeCode metricTypeCode;
    private Double quantity;
}
