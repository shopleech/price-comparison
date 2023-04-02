package com.shopleech.publicapi.dal.dto;

import com.shopleech.base.config.type.MetricTypeCode;
import lombok.*;

/**
 * @author Ahto Jalak
 * @since 07.02.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MetricDALDTO {

    private Integer id;

//    private ProductDALDTO product;

//    private OfferDALDTO offer;

//    private CategoryDALDTO category;

    private MetricTypeCode metricTypeCode;
    private Double quantity;
}
