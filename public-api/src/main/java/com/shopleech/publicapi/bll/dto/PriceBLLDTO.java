package com.shopleech.publicapi.bll.dto;

import com.shopleech.base.config.CurrencyTypeCode;
import com.shopleech.base.config.PriceTypeCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 05.02.2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceBLLDTO {

    private Integer id;
    private PriceTypeCode priceTypeCode;
    private Double quantity;
    private Double amount;
    private CurrencyTypeCode currency;
}