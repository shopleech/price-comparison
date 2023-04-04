package com.shopleech.publicapi.bll.dto;

import com.shopleech.base.config.type.WatchlistTypeCode;
import lombok.*;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WatchlistBLLDTO {
    private Integer id;

    private CustomerBLLDTO customer;

    private ProductBLLDTO product;

    private WatchlistTypeCode watchlistTypeCode;
}
