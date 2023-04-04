package com.shopleech.publicapi.bll.dto;

import com.shopleech.base.config.type.CurrencyTypeCode;
import com.shopleech.base.config.type.PriceTypeCode;
import lombok.*;

/**
 * @author Ahto Jalak
 * @since 05.02.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PriceBLLDTO {
    private Integer id;

    private OfferBLLDTO offer;

    private PriceTypeCode priceTypeCode;
    private Double quantity;
    private Double amount;
    private CurrencyTypeCode currency;
}
