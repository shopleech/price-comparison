package com.shopleech.publicapi.bll.dto;

import com.shopleech.base.config.type.FeatureTypeCode;
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
public class FeatureBLLDTO {
    private Integer id;

    private OfferBLLDTO offer;

    private FeatureTypeCode featureTypeCode;
    private String name;
    private String description;
}
