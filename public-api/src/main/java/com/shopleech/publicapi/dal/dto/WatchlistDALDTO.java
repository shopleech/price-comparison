package com.shopleech.publicapi.dal.dto;

import com.shopleech.base.config.WatchTypeCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WatchlistDALDTO {

    private Integer id;
    private WatchTypeCode watchTypeCode;
}
