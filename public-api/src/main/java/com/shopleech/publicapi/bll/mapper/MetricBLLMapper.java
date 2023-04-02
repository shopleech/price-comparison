package com.shopleech.publicapi.bll.mapper;

import com.shopleech.publicapi.bll.dto.MetricBLLDTO;
import com.shopleech.publicapi.dal.dto.MetricDALDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 23.03.2023
 */
@Component
public class MetricBLLMapper {

//    @Autowired
//    OfferBLLMapper offerBLLMapper;
//    @Autowired
//    ProductBLLMapper productBLLMapper;
//    @Autowired
//    CategoryBLLMapper categoryBLLMapper;

    public List<MetricBLLDTO> mapToDto(List<MetricDALDTO> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public MetricBLLDTO mapToDto(MetricDALDTO c) {

        MetricBLLDTO dto = new MetricBLLDTO();
        dto.setId(c.getId());
//        productBLLMapper.mapToDto(c.getProduct()),
//        offerBLLMapper.mapToDto(c.getOffer()),
//        categoryBLLMapper.mapToDto(c.getCategory()),
        dto.setMetricTypeCode(c.getMetricTypeCode());
        dto.setQuantity(c.getQuantity());

        return dto;
    }

    public List<MetricDALDTO> mapToEntity(List<MetricBLLDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public MetricDALDTO mapToEntity(MetricBLLDTO entity) {

        MetricDALDTO c = new MetricDALDTO();
        c.setId(entity.getId());
        // c.setProduct(productBLLMapper.mapToEntity(entity.getProduct()));
        // c.setOffer(offerBLLMapper.mapToEntity(entity.getOffer()));
        // c.setCategory(categoryBLLMapper.mapToEntity(entity.getCategory()));
        c.setMetricTypeCode(entity.getMetricTypeCode());
        c.setQuantity(entity.getQuantity());

        return c;
    }
}
