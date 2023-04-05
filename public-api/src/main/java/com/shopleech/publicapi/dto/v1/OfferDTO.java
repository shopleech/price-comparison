package com.shopleech.publicapi.dto.v1;

import com.shopleech.base.config.type.BarcodeTypeCode;
import com.shopleech.publicapi.domain.Account;
import com.shopleech.publicapi.domain.Product;
import com.shopleech.publicapi.domain.Shop;
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
public class OfferDTO {
    private Integer id;

    private Account account;

    private Product product;

    private Shop shop;

    private String barcode;
    private BarcodeTypeCode barcodeTypeCode;
    private String name;
    private String description;
    private String url;
}
