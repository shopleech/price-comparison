package com.shopleech.publicapi.bll.service.model;

import com.shopleech.publicapi.domain.Metric;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 05.04.2023
 */
public interface IMetricService {
    List<Metric> getAll();

    Metric get(Integer id) throws Exception;

    Metric add(Metric metric);

    Metric update(Integer id, Metric metric);

    Integer remove(Integer id);
}
