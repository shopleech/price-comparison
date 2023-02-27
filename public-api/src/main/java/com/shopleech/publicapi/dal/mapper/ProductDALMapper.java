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

    public List<ProductDALDTO> mapToDto(List<Product> products) {
        return products.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public ProductDALDTO mapToDto(Product c) {
        return new ProductDALDTO(
                c.getId(),
                c.getProductTypeCode(),
                c.getBarcode(),
                c.getBarcodeTypeCode(),
                c.getName(),
                c.getDescription()
        );
    }

    public Product mapToEntity(ProductDALDTO newProduct) {
        Product entity = new Product();
        entity.setId(newProduct.getId());
        entity.setProductTypeCode(newProduct.getProductTypeCode());
        entity.setBarcode(newProduct.getBarcode());
        entity.setBarcodeTypeCode(newProduct.getBarcodeTypeCode());
        entity.setName(newProduct.getName());
        entity.setDescription(newProduct.getDescription());
        return entity;
    }

}
