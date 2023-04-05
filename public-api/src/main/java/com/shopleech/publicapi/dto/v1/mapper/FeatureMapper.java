package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.domain.Feature;
import com.shopleech.publicapi.dto.v1.FeatureDTO;
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
public class FeatureMapper {
    Logger logger = LoggerFactory.getLogger(FeatureMapper.class);

    public List<FeatureDTO> mapToDto(List<Feature> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public FeatureDTO mapToDto(Feature c) {
        FeatureDTO dto = new FeatureDTO();
        dto.setId(c.getId());
        // dto.setParentFeature();
        //dto.setName(c.getName());
        // dto.setFeatureTypeCode();

        return dto;
    }

    public List<Feature> mapToEntity(List<FeatureDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Feature mapToEntity(FeatureDTO entity) {

        Feature c = new Feature();
        c.setId(entity.getId());
        // c.setParentFeature();
        //c.setName(entity.getName());
        // c.setFeatureTypeCode();

        return c;
    }
}
