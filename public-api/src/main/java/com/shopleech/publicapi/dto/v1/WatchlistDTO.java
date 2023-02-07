package com.shopleech.publicapi.dto.v1;

import com.shopleech.base.config.WatchTypeCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WatchlistDTO {

   private Integer id;
   private WatchTypeCode watchTypeCode;
}
