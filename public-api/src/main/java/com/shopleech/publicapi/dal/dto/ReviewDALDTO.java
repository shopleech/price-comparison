package com.shopleech.publicapi.dal.dto;

import com.shopleech.base.config.type.ReviewTypeCode;
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
public class ReviewDALDTO {

    private Integer id;

//    private CustomerDALDTO customer;

//    private ProductDALDTO product;

    private ReviewTypeCode reviewTypeCode;
    private Integer score;
    private String description;
}
