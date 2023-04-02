package com.shopleech.publicapi.dal.mapper;

import com.shopleech.publicapi.dal.dto.OfferDALDTO;
import com.shopleech.publicapi.domain.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 23.03.2023
 */
@Component
public class OfferDALMapper {

//    @Autowired
//    AccountDALMapper accountDALMapper;
//    @Autowired
//    ProductDALMapper productDALMapper;
//    @Autowired
//    FeatureDALMapper featureDALMapper;
//    @Autowired
//    PriceDALMapper priceDALMapper;
//    @Autowired
//    MetricDALMapper metricDALMapper;

    public List<OfferDALDTO> mapToDto(List<Offer> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public OfferDALDTO mapToDto(Offer c) {

        OfferDALDTO dto = new OfferDALDTO();
        dto.setId(c.getId());
        // accountDALMapper.mapToDto(c.getAccount()),
        // productDALMapper.mapToDto(c.getProduct()),
        dto.setBarcode(c.getBarcode());
        dto.setBarcodeTypeCode(c.getBarcodeTypeCode());
        dto.setName(c.getName());
        dto.setDescription(c.getDescription());
        dto.setUrl(c.getUrl());
        // featureDALMapper.mapToDto(c.getFeatures()),
        // priceDALMapper.mapToDto(c.getPrices()),
        // metricDALMapper.mapToDto(c.getMetrics())

        return dto;
    }

    public List<Offer> mapToEntity(List<OfferDALDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Offer mapToEntity(OfferDALDTO entity) {

        Offer c = new Offer();
        c.setId(entity.getId());
//        c.setAccount(accountDALMapper.mapToEntity(entity.getAccount()));
//        c.setProduct(productDALMapper.mapToEntity(entity.getProduct()));
        c.setBarcode(entity.getBarcode());
        c.setBarcodeTypeCode(entity.getBarcodeTypeCode());
        c.setName(entity.getName());
        c.setDescription(entity.getDescription());
        c.setUrl(entity.getUrl());
//        c.setFeatures(featureDALMapper.mapToEntity(entity.getFeatures()));
//        c.setPrices(priceDALMapper.mapToEntity(entity.getPrices()));
//        c.setMetrics(metricDALMapper.mapToEntity(entity.getMetrics()));

        return c;
    }
}
