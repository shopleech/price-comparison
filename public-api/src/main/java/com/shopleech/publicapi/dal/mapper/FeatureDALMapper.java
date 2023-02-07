package com.shopleech.publicapi.dal.mapper;

import com.shopleech.publicapi.dal.dto.FeatureDALDTO;
import com.shopleech.publicapi.domain.Feature;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class FeatureDALMapper {

   public List<FeatureDALDTO> mapToDto(List<Feature> features) {
      return features.stream()
              .map(this::mapToDto).collect(Collectors.toList());
   }

   public FeatureDALDTO mapToDto(Feature c) {
      return new FeatureDALDTO(
              c.getId(),
              c.getFeatureTypeCode(),
              c.getName(),
              c.getDescription()
      );
   }

   public Feature mapToEntity(FeatureDALDTO newFeature) {
      Feature entity = new Feature();
      entity.setId(newFeature.getId());
      entity.setFeatureTypeCode(newFeature.getFeatureTypeCode());
      entity.setName(newFeature.getName());
      entity.setDescription(newFeature.getDescription());
      return entity;
   }

}
