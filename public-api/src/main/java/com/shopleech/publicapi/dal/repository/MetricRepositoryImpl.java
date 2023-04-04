package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.MetricDALDTO;
import com.shopleech.publicapi.dal.mapper.MetricDALMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Component
public class MetricRepositoryImpl implements MetricRepositoryCustom {

    private MetricRepository metricRepository;

    private MetricDALMapper metricDALMapper;

    @Override
    public void addMetric(MetricDALDTO metric) {
        // metricRepository.add(metricDALMapper.mapToEntity(metric));
    }

    @Override
    public MetricDALDTO getMetricById(Integer id) {
        return metricDALMapper.mapToDto(metricRepository.getReferenceById(id));
    }

    @Override
    public List<MetricDALDTO> getAllMetrics() {
        return metricDALMapper.mapToDto(metricRepository.findAll());
    }
}
