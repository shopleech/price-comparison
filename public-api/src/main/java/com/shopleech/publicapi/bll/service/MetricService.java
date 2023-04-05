package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.service.model.IMetricService;
import com.shopleech.publicapi.dal.repository.MetricRepository;
import com.shopleech.publicapi.domain.Metric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 05.04.2023
 */
@Service
public class MetricService implements IMetricService {
    Logger logger = LoggerFactory.getLogger(MetricService.class);

    @Autowired
    protected MetricRepository metricRepository;

    @Override
    public List<Metric> getAll() {
        return metricRepository.findAll();
    }

    @Override
    public Metric get(Integer id) throws Exception {
        var item = metricRepository.findById(id);

        if (item.isEmpty()) {
            throw new Exception("metric not found");
        }

        return item.get();
    }

    @Override
    public Metric add(Metric metric) {
        return metric;
    }

    @Override
    public Metric update(Integer id, Metric metric) {
        return metric;
    }

    @Override
    public Integer remove(Integer id) {
        return id;
    }
}
