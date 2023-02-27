package com.shopleech.publicapi.dal.mapper;

import com.shopleech.publicapi.dal.dto.MetricDALDTO;
import com.shopleech.publicapi.domain.Metric;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class MetricDALMapper {

    public List<MetricDALDTO> mapToDto(List<Metric> metrics) {
        return metrics.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public MetricDALDTO mapToDto(Metric c) {
        return new MetricDALDTO(
                c.getId(),
                c.getMetricTypeCode(),
                c.getQuantity()
        );
    }

    public Metric mapToEntity(MetricDALDTO newMetric) {
        Metric entity = new Metric();
        entity.setId(newMetric.getId());
        entity.setMetricTypeCode(newMetric.getMetricTypeCode());
        entity.setQuantity(newMetric.getQuantity());
        return entity;
    }

}
