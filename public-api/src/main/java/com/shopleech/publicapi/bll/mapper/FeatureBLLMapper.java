package com.shopleech.publicapi.bll.mapper;

import com.shopleech.publicapi.bll.dto.FeatureBLLDTO;
import com.shopleech.publicapi.dal.dto.FeatureDALDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 23.03.2023
 */
@Component
public class FeatureBLLMapper {

//    @Autowired
//    OfferBLLMapper offerBLLMapper;

    public List<FeatureBLLDTO> mapToDto(List<FeatureDALDTO> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public FeatureBLLDTO mapToDto(FeatureDALDTO c) {
        FeatureBLLDTO dto = new FeatureBLLDTO();
        dto.setId(c.getId());
        //offerBLLMapper.mapToDto(c.getOffer())
        dto.setFeatureTypeCode(c.getFeatureTypeCode());
        dto.setName(c.getName());
        dto.setDescription(c.getName());

        return dto;
    }

    public List<FeatureDALDTO> mapToEntity(List<FeatureBLLDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public FeatureDALDTO mapToEntity(FeatureBLLDTO entity) {

        FeatureDALDTO c = new FeatureDALDTO();
        c.setId(entity.getId());
        // c.setOffer(offerBLLMapper.mapToEntity(entity.getOffer()));
        c.setFeatureTypeCode(entity.getFeatureTypeCode());
        c.setName(entity.getName());
        c.setDescription(entity.getDescription());

        return c;
    }
}
