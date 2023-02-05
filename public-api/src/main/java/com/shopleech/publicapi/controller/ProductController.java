package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.dto.AddProductRequest;
import com.shopleech.publicapi.bll.dto.ProductResponse;
import com.shopleech.publicapi.bll.service.ProductService;
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

   @GetMapping("/mylist")
   public ResponseEntity<String> demo() {
      return ResponseEntity.ok("Hello");
   }

   @PostMapping("/add")
   public ResponseEntity<ProductResponse> add(@RequestBody AddProductRequest request) {
      return ResponseEntity.ok(service.register(request));
   }
}
