package com.shopleech.publicapi.dto.v1;

import com.shopleech.base.config.type.ReviewTypeCode;
import com.shopleech.publicapi.domain.Customer;
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
public class ReviewDTO {
    private Integer id;
    private Integer productId;
    private ProductDTO product;
    private ReviewTypeCode reviewTypeCode;
    private Integer score;
    private String description;
}
