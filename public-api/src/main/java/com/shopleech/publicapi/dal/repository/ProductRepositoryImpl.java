package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.ProductDALDTO;
import com.shopleech.publicapi.dal.mapper.ProductDALMapper;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
public class ProductRepositoryImpl implements ProductRepositoryCustom {

   private ProductRepository productRepository;

   private ProductDALMapper productDALMapper;

   @Override
   public void addProduct(ProductDALDTO product) {
      // productRepository.add(productDALMapper.mapToEntity(product));
   }

   @Override
   public ProductDALDTO getProductById(Integer id) {
      return productDALMapper.mapToDto(productRepository.getReferenceById(id));
   }

   @Override
   public List<ProductDALDTO> getAllProducts() {
      return productDALMapper.mapToDto(productRepository.findAll());
   }
}
