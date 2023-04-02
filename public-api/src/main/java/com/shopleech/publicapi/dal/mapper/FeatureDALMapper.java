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

//    @Autowired
//    OfferDALMapper offerDALMapper;

    public List<FeatureDALDTO> mapToDto(List<Feature> features) {
        return features.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public FeatureDALDTO mapToDto(Feature c) {

        FeatureDALDTO dto = new FeatureDALDTO();
        dto.setId(c.getId());
        //offerDALMapper.mapToDto(c.getOffer())
        dto.setFeatureTypeCode(c.getFeatureTypeCode());
        dto.setName(c.getName());
        dto.setDescription(c.getName());

        return dto;
    }

    public List<Feature> mapToEntity(List<FeatureDALDTO> features) {
        return features.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Feature mapToEntity(FeatureDALDTO entity) {

        Feature c = new Feature();
        c.setId(entity.getId());
//        c.setOffer(offerDALMapper.mapToEntity(entity.getOffer()));
        c.setFeatureTypeCode(entity.getFeatureTypeCode());
        c.setName(entity.getName());
        c.setDescription(entity.getDescription());

        return c;
    }

}
