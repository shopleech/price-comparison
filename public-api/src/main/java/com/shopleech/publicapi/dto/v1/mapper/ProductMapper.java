package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.bll.service.CategoryService;
import com.shopleech.publicapi.bll.service.PriceService;
import com.shopleech.publicapi.domain.Product;
import com.shopleech.publicapi.dto.v1.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class ProductMapper {
    Logger logger = LoggerFactory.getLogger(ProductMapper.class);

    @Autowired
    CategoryService categoryService;
    @Autowired
    PriceService priceService;

    public List<ProductDTO> mapToDto(List<Product> products) {
        return products.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public ProductDTO mapToDto(Product c) {
        var dto = new ProductDTO();
        dto.setId(c.getId());
        if (c.getCategory() != null) {
            dto.setCategoryId(c.getCategory().getId());
        }
        dto.setProductTypeCode(c.getProductTypeCode());
        dto.setBarcode(c.getBarcode());
        dto.setBarcodeTypeCode(c.getBarcodeTypeCode());
        dto.setName(c.getName());
        dto.setDescription(c.getDescription());
        dto.setMinPrice(priceService.getMinPriceByBarcode(c.getBarcode()));

        return dto;
    }

    public Product mapToEntity(ProductDTO newProduct) {
        Product entity = new Product();
        entity.setId(newProduct.getId());
        try {
            entity.setCategory(categoryService.get(newProduct.getCategoryId()));
        } catch (Exception e) {
            logger.error("product mapper failed");
        }
        entity.setProductTypeCode(newProduct.getProductTypeCode());
        entity.setBarcode(newProduct.getBarcode());
        entity.setBarcodeTypeCode(newProduct.getBarcodeTypeCode());
        entity.setName(newProduct.getName());
        entity.setDescription(newProduct.getDescription());

        return entity;
    }

}
