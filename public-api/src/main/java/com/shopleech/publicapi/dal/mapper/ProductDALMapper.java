package com.shopleech.publicapi.dal.mapper;

import com.shopleech.publicapi.dal.dto.ProductDALDTO;
import com.shopleech.publicapi.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class ProductDALMapper {

//    @Autowired
//    CategoryDALMapper categoryDALMapper;
//    @Autowired
//    WatchlistDALMapper watchlistDALMapper;
//    @Autowired
//    AlarmDALMapper alarmDALMapper;
//    @Autowired
//    ReviewDALMapper reviewDALMapper;
//    @Autowired
//    OfferDALMapper offerDALMapper;
//    @Autowired
//    MetricDALMapper metricDALMapper;

    public List<ProductDALDTO> mapToDto(List<Product> products) {
        return products.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public ProductDALDTO mapToDto(Product c) {

        ProductDALDTO dto = new ProductDALDTO();
        dto.setId(c.getId());
        // categoryDALMapper.mapToDto(c.getCategory()),
        dto.setProductTypeCode(c.getProductTypeCode());
        dto.setBarcode(c.getBarcode());
        dto.setBarcodeTypeCode(c.getBarcodeTypeCode());
        dto.setName(c.getName());
        dto.setDescription(c.getDescription());
        // watchlistDALMapper.mapToDto(c.getWatchlists()),
        // alarmDALMapper.mapToDto(c.getAlarms()),
        // reviewDALMapper.mapToDto(c.getReviews()),
        // offerDALMapper.mapToDto(c.getOffers()),
        // metricDALMapper.mapToDto(c.getMetrics())

        return dto;
    }

    public List<Product> mapToEntity(List<ProductDALDTO> products) {
        return products.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Product mapToEntity(ProductDALDTO newProduct) {

        Product entity = new Product();
        entity.setId(newProduct.getId());
//        entity.setCategory(categoryDALMapper.mapToEntity(newProduct.getCategory()));
        entity.setProductTypeCode(newProduct.getProductTypeCode());
        entity.setBarcode(newProduct.getBarcode());
        entity.setBarcodeTypeCode(newProduct.getBarcodeTypeCode());
        entity.setName(newProduct.getName());
        entity.setDescription(newProduct.getDescription());
//        entity.setWatchlists(watchlistDALMapper.mapToEntity(newProduct.getWatchlists()));
//        entity.setAlarms(alarmDALMapper.mapToEntity(newProduct.getAlarms()));
//        entity.setReviews(reviewDALMapper.mapToEntity(newProduct.getReviews()));
//        entity.setOffers(offerDALMapper.mapToEntity(newProduct.getOffers()));
//        entity.setMetrics(metricDALMapper.mapToEntity(newProduct.getMetrics()));

        return entity;
    }

}
