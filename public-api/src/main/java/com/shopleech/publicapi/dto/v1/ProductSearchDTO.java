package com.shopleech.publicapi.dto.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 19.03.2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchDTO {
    private String keyword;
}
