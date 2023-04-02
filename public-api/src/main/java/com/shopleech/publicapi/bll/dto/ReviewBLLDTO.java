package com.shopleech.publicapi.bll.dto;

import com.shopleech.base.config.type.ReviewTypeCode;
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
public class ReviewBLLDTO {

    private Integer id;

    private CustomerBLLDTO customer;

    private ProductBLLDTO product;

    private ReviewTypeCode reviewTypeCode;
    private Integer score;
    private String description;
}
