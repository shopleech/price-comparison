package com.shopleech.publicapi.bll.dto;

import com.shopleech.base.config.type.MetricTypeCode;
import lombok.*;

/**
 * @author Ahto Jalak
 * @since 23.03.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MetricBLLDTO {
    private Integer id;

    private ProductBLLDTO product;

    private OfferBLLDTO offer;

    private CategoryBLLDTO category;

    private MetricTypeCode metricTypeCode;
    private Double quantity;
}
