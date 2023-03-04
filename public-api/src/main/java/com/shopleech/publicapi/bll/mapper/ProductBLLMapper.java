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

    public List<ProductBLLDTO> mapToDto(List<ProductDALDTO> products) {
        return products.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public ProductBLLDTO mapToDto(ProductDALDTO c) {
        return new ProductBLLDTO(
                c.getId(),
                c.getProductTypeCode(),
                c.getBarcode(),
                c.getBarcodeTypeCode(),
                c.getName(),
                c.getDescription()
        );
    }

    public ProductDALDTO mapToEntity(ProductBLLDTO newProduct) {
        ProductDALDTO entity = new ProductDALDTO();
        entity.setId(newProduct.getId());
        entity.setProductTypeCode(newProduct.getProductTypeCode());
        entity.setBarcode(newProduct.getBarcode());
        entity.setBarcodeTypeCode(newProduct.getBarcodeTypeCode());
        entity.setName(newProduct.getName());
        entity.setDescription(newProduct.getDescription());
        return entity;
    }
}
