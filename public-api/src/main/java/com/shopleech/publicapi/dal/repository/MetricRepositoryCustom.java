package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.MetricDALDTO;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 07.02.2023
 */
public interface MetricRepositoryCustom {

   void addMetric(MetricDALDTO mapToEntity);

   MetricDALDTO getMetricById(Integer id);

   List<MetricDALDTO> getAllMetrics();
}
