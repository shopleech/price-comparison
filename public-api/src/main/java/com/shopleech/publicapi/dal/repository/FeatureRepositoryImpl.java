package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.FeatureDALDTO;
import com.shopleech.publicapi.dal.mapper.FeatureDALMapper;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
public class FeatureRepositoryImpl implements FeatureRepositoryCustom {

   private FeatureRepository featureRepository;

   private FeatureDALMapper featureDALMapper;

   @Override
   public void addFeature(FeatureDALDTO feature) {
      // featureRepository.add(featureDALMapper.mapToEntity(feature));
   }

   @Override
   public FeatureDALDTO getFeatureById(Integer id) {
      return featureDALMapper.mapToDto(featureRepository.getReferenceById(id));
   }

   @Override
   public List<FeatureDALDTO> getAllFeatures() {
      return featureDALMapper.mapToDto(featureRepository.findAll());
   }
}
