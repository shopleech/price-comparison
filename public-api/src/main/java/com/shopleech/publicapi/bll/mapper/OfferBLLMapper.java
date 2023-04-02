package com.shopleech.publicapi.bll.mapper;

import com.shopleech.publicapi.bll.dto.OfferBLLDTO;
import com.shopleech.publicapi.dal.dto.OfferDALDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 23.03.2023
 */
@Component
public class OfferBLLMapper {

//    @Autowired
//    AccountBLLMapper accountBLLMapper;
//    @Autowired
//    ProductBLLMapper productBLLMapper;
//    @Autowired
//    FeatureBLLMapper featureBLLMapper;
//    @Autowired
//    PriceBLLMapper priceBLLMapper;
//    @Autowired
//    MetricBLLMapper metricBLLMapper;

    public List<OfferBLLDTO> mapToDto(List<OfferDALDTO> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public OfferBLLDTO mapToDto(OfferDALDTO c) {
        OfferBLLDTO dto = new OfferBLLDTO();
        dto.setId(c.getId());
        // accountBLLMapper.mapToDto(c.getAccount()),
        // productBLLMapper.mapToDto(c.getProduct()),
        dto.setBarcode(c.getBarcode());
        dto.setBarcodeTypeCode(c.getBarcodeTypeCode());
        dto.setName(c.getName());
        dto.setDescription(c.getDescription());
        dto.setUrl(c.getUrl());
        // featureBLLMapper.mapToDto(c.getFeatures()),
        // priceBLLMapper.mapToDto(c.getPrices()),
        // metricBLLMapper.mapToDto(c.getMetrics())

        return dto;
    }

    public List<OfferDALDTO> mapToEntity(List<OfferBLLDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public OfferDALDTO mapToEntity(OfferBLLDTO entity) {

        OfferDALDTO c = new OfferDALDTO();
        c.setId(entity.getId());
        // c.setAccount(accountBLLMapper.mapToEntity(entity.getAccount()));
        // c.setProduct(productBLLMapper.mapToEntity(entity.getProduct()));
        c.setBarcode(entity.getBarcode());
        c.setBarcodeTypeCode(entity.getBarcodeTypeCode());
        c.setName(entity.getName());
        c.setDescription(entity.getDescription());
        c.setUrl(entity.getUrl());
        // c.setFeatures(featureBLLMapper.mapToEntity(entity.getFeatures()));
        // c.setPrices(priceBLLMapper.mapToEntity(entity.getPrices()));
        // c.setMetrics(metricBLLMapper.mapToEntity(entity.getMetrics()));

        return c;
    }
}
