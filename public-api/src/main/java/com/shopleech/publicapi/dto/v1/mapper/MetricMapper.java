package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.bll.service.CategoryService;
import com.shopleech.publicapi.bll.service.OfferService;
import com.shopleech.publicapi.bll.service.ProductService;
import com.shopleech.publicapi.domain.Metric;
import com.shopleech.publicapi.dto.v1.MetricDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 05.04.2023
 */
@Component
public class MetricMapper {
    Logger logger = LoggerFactory.getLogger(MetricMapper.class);

    @Autowired
    ProductService productService;
    @Autowired
    OfferService offerService;
    @Autowired
    CategoryService categoryService;

    public List<MetricDTO> mapToDto(List<Metric> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public MetricDTO mapToDto(Metric c) {
        MetricDTO dto = new MetricDTO();
        dto.setId(c.getId());
        if (c.getProduct() != null) {
            dto.setProductId(c.getProduct().getId());
        }
        if (c.getOffer() != null) {
            dto.setOfferId(c.getOffer().getId());
        }
        if (c.getCategory() != null) {
            dto.setCategoryId(c.getCategory().getId());
        }
        dto.setMetricTypeCode(c.getMetricTypeCode());
        dto.setQuantity(c.getQuantity());

        return dto;
    }

    public List<Metric> mapToEntity(List<MetricDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Metric mapToEntity(MetricDTO entity) {
        Metric c = new Metric();
        c.setId(entity.getId());
        try {
            c.setProduct(productService.get(entity.getProductId()));
            c.setOffer(offerService.get(entity.getOfferId()));
            c.setCategory(categoryService.get(entity.getCategoryId()));
        } catch (Exception e) {
            logger.error("metric mapper failed");
        }
        c.setMetricTypeCode(entity.getMetricTypeCode());
        c.setQuantity(entity.getQuantity());

        return c;
    }
}
