package com.shopleech.publicapi.dal.dto;

import com.shopleech.base.config.ReviewTypeCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 07.02.2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDALDTO {

    private Integer id;
    private ReviewTypeCode reviewTypeCode;
    private Integer score;
    private String description;
}
