package com.shopleech.publicapi.bll.mapper;

import com.shopleech.publicapi.bll.dto.ProductBLLDTO;
import com.shopleech.publicapi.dal.dto.ProductDALDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 07.02.2023
 */
@Component
public class ProductBLLMapper {

//    @Autowired
//    CategoryBLLMapper categoryBLLMapper;
//    @Autowired
//    WatchlistBLLMapper watchlistBLLMapper;
//    @Autowired
//    AlarmBLLMapper alarmBLLMapper;
//    @Autowired
//    ReviewBLLMapper reviewBLLMapper;
//    @Autowired
//    OfferBLLMapper offerBLLMapper;
//    @Autowired
//    MetricBLLMapper metricBLLMapper;

    public List<ProductBLLDTO> mapToDto(List<ProductDALDTO> products) {
        return products.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public ProductBLLDTO mapToDto(ProductDALDTO c) {
        ProductBLLDTO dto = new ProductBLLDTO();
        dto.setId(c.getId());
        // categoryBLLDALMapper.mapToDto(c.getCategory()),
        dto.setProductTypeCode(c.getProductTypeCode());
        dto.setBarcode(c.getBarcode());
        dto.setBarcodeTypeCode(c.getBarcodeTypeCode());
        dto.setName(c.getName());
        dto.setDescription(c.getDescription());
        // watchlistBLLMapper.mapToDto(c.getWatchlists()),
        // alarmBLLMapper.mapToDto(c.getAlarms()),
        // reviewBLLMapper.mapToDto(c.getReviews()),
        // offerBLLMapper.mapToDto(c.getOffers()),
        // metricBLLMapper.mapToDto(c.getMetrics())

        return dto;
    }

    public List<ProductDALDTO> mapToEntity(List<ProductBLLDTO> products) {
        return products.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public ProductDALDTO mapToEntity(ProductBLLDTO newProduct) {

        ProductDALDTO entity = new ProductDALDTO();
        entity.setId(newProduct.getId());
        // entity.setCategory(categoryBLLMapper.mapToEntity(newProduct.getCategory()));
        entity.setProductTypeCode(newProduct.getProductTypeCode());
        entity.setBarcode(newProduct.getBarcode());
        entity.setBarcodeTypeCode(newProduct.getBarcodeTypeCode());
        entity.setName(newProduct.getName());
        entity.setDescription(newProduct.getDescription());
        // entity.setWatchlists(watchlistBLLMapper.mapToEntity(newProduct.getWatchlists()));
        // entity.setAlarms(alarmBLLMapper.mapToEntity(newProduct.getAlarms()));
        // entity.setReviews(reviewBLLMapper.mapToEntity(newProduct.getReviews()));
        // entity.setOffers(offerBLLMapper.mapToEntity(newProduct.getOffers()));
        // entity.setMetrics(metricBLLMapper.mapToEntity(newProduct.getMetrics()));

        return entity;
    }
}
