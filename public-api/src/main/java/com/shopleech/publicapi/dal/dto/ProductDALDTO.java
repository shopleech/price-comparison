package com.shopleech.publicapi.dal.dto;

import com.shopleech.base.config.type.BarcodeTypeCode;
import com.shopleech.base.config.type.ProductTypeCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 05.02.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDALDTO {

    private Integer id;
    private ProductTypeCode productTypeCode;
    private String barcode;
    private BarcodeTypeCode barcodeTypeCode;
    private String name;
    private String description;
}
