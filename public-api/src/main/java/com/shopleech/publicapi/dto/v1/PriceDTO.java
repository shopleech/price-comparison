package com.shopleech.publicapi.dto.v1;

import com.shopleech.base.config.type.CurrencyTypeCode;
import com.shopleech.base.config.type.PriceTypeCode;
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
public class PriceDTO {
    private Integer id;
    private PriceTypeCode priceTypeCode;
    private Double quantity;
    private Double amount;
    private CurrencyTypeCode currency;
}
