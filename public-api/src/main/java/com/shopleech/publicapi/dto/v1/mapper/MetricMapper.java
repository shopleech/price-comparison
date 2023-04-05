package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.domain.Metric;
import com.shopleech.publicapi.dto.v1.MetricDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 05.04.2023
 */
@Component
public class MetricMapper {
    Logger logger = LoggerFactory.getLogger(MetricMapper.class);

    public List<MetricDTO> mapToDto(List<Metric> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public MetricDTO mapToDto(Metric c) {
        MetricDTO dto = new MetricDTO();
        dto.setId(c.getId());
        // dto.setParentMetric();
        //dto.setName(c.getName());
        // dto.setMetricTypeCode();

        return dto;
    }

    public List<Metric> mapToEntity(List<MetricDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Metric mapToEntity(MetricDTO entity) {

        Metric c = new Metric();
        c.setId(entity.getId());
        // c.setParentMetric();
        //c.setName(entity.getName());
        // c.setMetricTypeCode();

        return c;
    }
}
