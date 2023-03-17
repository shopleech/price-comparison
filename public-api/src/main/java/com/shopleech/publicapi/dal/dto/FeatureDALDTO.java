package com.shopleech.publicapi.dal.dto;

import com.shopleech.base.config.type.FeatureTypeCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 07.02.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeatureDALDTO {

    private Integer id;
    private FeatureTypeCode featureTypeCode;
    private String name;
    private String description;
}
