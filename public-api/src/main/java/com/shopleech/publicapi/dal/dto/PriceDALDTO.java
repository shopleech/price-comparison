package com.shopleech.publicapi.dal.dto;

import com.shopleech.base.config.type.CurrencyTypeCode;
import com.shopleech.base.config.type.PriceTypeCode;
import lombok.*;

/**
 * @author Ahto Jalak
 * @since 05.02.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PriceDALDTO {

    private Integer id;

//    private OfferDALDTO offer;

    private PriceTypeCode priceTypeCode;
    private Double quantity;
    private Double amount;
    private CurrencyTypeCode currency;
}
