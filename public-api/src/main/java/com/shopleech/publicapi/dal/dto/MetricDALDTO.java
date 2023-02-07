package com.shopleech.publicapi.dal.dto;

import com.shopleech.base.config.MetricTypeCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 07.02.2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetricDALDTO {

   private Integer id;
   private MetricTypeCode metricTypeCode;
   private Double quantity;
}
