package com.shopleech.publicapi.dto.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 25.03.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImportDTO {
    Integer storeId;

    List<ProductImportItemDTO> productImportItems;
}
