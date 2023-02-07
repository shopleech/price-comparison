package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.bll.dto.ProductBLLDTO;
import com.shopleech.publicapi.dto.v1.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class ProductMapper {

   public List<ProductDTO> mapToDto(List<ProductBLLDTO> products) {
      return products.stream()
              .map(this::mapToDto).collect(Collectors.toList());
   }

   public ProductDTO mapToDto(ProductBLLDTO c) {
      return new ProductDTO(
              c.getId(),
              c.getProductTypeCode(),
              c.getBarcode(),
              c.getBarcodeTypeCode(),
              c.getName(),
              c.getDescription()
      );
   }

   public ProductBLLDTO mapToEntity(ProductDTO newProduct) {
      ProductBLLDTO entity = new ProductBLLDTO();
      entity.setId(newProduct.getId());
      entity.setProductTypeCode(newProduct.getProductTypeCode());
      entity.setBarcode(newProduct.getBarcode());
      entity.setBarcodeTypeCode(newProduct.getBarcodeTypeCode());
      entity.setName(newProduct.getName());
      entity.setDescription(newProduct.getDescription());
      return entity;
   }

}
