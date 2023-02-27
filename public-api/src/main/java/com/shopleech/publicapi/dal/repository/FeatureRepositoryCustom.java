package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.FeatureDALDTO;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 07.02.2023
 */
public interface FeatureRepositoryCustom {

    void addFeature(FeatureDALDTO mapToEntity);

    FeatureDALDTO getFeatureById(Integer id);

    List<FeatureDALDTO> getAllFeatures();
}
