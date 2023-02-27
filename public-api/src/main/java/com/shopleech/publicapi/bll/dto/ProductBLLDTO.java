package com.shopleech.publicapi.bll.dto;

import com.shopleech.base.config.BarcodeTypeCode;
import com.shopleech.base.config.ProductTypeCode;
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
public class ProductBLLDTO {

    private Integer id;
    private ProductTypeCode productTypeCode;
    private String barcode;
    private BarcodeTypeCode barcodeTypeCode;
    private String name;
    private String description;
}
