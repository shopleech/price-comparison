package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.service.ProductService;
import com.shopleech.publicapi.dto.v1.ProductDTO;
import com.shopleech.publicapi.dto.v1.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ahto Jalak
 * @since 04.02.2023
 */
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

   private final ProductService service;

   private final ProductMapper mapper;

   @GetMapping("/mylist")
   public ResponseEntity<String> demo() {
      return ResponseEntity.ok("Hello");
   }

   @PostMapping("/add")
   public ResponseEntity<ProductDTO> add(@RequestBody ProductDTO request) {
      service.createProduct(mapper.mapToEntity(request));
      return ResponseEntity.ok(request);
   }
}
