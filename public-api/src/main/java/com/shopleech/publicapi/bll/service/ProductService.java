package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.dto.AddProductRequest;
import com.shopleech.publicapi.bll.dto.ProductResponse;
import com.shopleech.publicapi.dal.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Ahto Jalak
 * @since 04.02.2023
 */
@Service
@RequiredArgsConstructor
public class ProductService {

   private final ProductRepository repository;

   public ProductResponse register(AddProductRequest request) {
      return new ProductResponse();
   }
}
