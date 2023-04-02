package com.shopleech.publicapi.dal.mapper;

import com.shopleech.publicapi.dal.dto.MetricDALDTO;
import com.shopleech.publicapi.domain.Metric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class MetricDALMapper {

//    @Autowired
//    OfferDALMapper offerDALMapper;
//    @Autowired
//    ProductDALMapper productDALMapper;
//    @Autowired
//    CategoryDALMapper categoryDALMapper;

    public List<MetricDALDTO> mapToDto(List<Metric> metrics) {
        return metrics.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public MetricDALDTO mapToDto(Metric c) {

        MetricDALDTO dto = new MetricDALDTO();
        dto.setId(c.getId());
//        productDALMapper.mapToDto(c.getProduct()),
//        offerDALMapper.mapToDto(c.getOffer()),
//        categoryDALMapper.mapToDto(c.getCategory()),
        dto.setMetricTypeCode(c.getMetricTypeCode());
        dto.setQuantity(c.getQuantity());

        return dto;
    }

    public List<Metric> mapToEntity(List<MetricDALDTO> metrics) {
        return metrics.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Metric mapToEntity(MetricDALDTO entity) {

        Metric c = new Metric();
        c.setId(entity.getId());
//        c.setProduct(productDALMapper.mapToEntity(entity.getProduct()));
//        c.setOffer(offerDALMapper.mapToEntity(entity.getOffer()));
//        c.setCategory(categoryDALMapper.mapToEntity(entity.getCategory()));
        c.setMetricTypeCode(entity.getMetricTypeCode());
        c.setQuantity(entity.getQuantity());

        return c;
    }

}
