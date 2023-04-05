package com.shopleech.publicapi.bll.service.model;

import com.shopleech.publicapi.domain.Feature;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 05.04.2023
 */
public interface IFeatureService {
    Integer remove(Integer id);

    Feature update(Integer id, Feature feature);

    Feature add(Feature feature);

    Feature get(Integer id) throws Exception;

    List<Feature> getAll();
}
