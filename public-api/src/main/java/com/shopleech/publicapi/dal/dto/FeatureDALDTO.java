package com.shopleech.publicapi.dal.dto;

import com.shopleech.base.config.type.FeatureTypeCode;
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
public class FeatureDALDTO {

    private Integer id;

//    private OfferDALDTO offer;

    private FeatureTypeCode featureTypeCode;
    private String name;
    private String description;
}
