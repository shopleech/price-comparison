package com.shopleech.publicapi.dto.v1;

import com.shopleech.base.config.type.FeatureTypeCode;
import com.shopleech.publicapi.domain.Offer;
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
public class FeatureDTO {
    private Integer id;

    private Offer offer;

    private FeatureTypeCode featureTypeCode;
    private String name;
    private String description;
}
