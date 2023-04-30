package com.shopleech.publicapi.dto.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 25.03.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImportItemDTO {
    private String barcode;
    private String productNo;
    private String name;
    private String description;
    private Double price;
    private String url;
}
