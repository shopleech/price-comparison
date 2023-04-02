package com.shopleech.publicapi.dal.dto;

import com.shopleech.base.config.type.WatchlistTypeCode;
import lombok.*;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WatchlistDALDTO {

    private Integer id;

//    private CustomerDALDTO customer;

//    private ProductDALDTO product;

    private WatchlistTypeCode watchlistTypeCode;
}
