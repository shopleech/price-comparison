package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.service.ProductService;
import com.shopleech.publicapi.dto.v1.ProductDTO;
import com.shopleech.publicapi.dto.v1.mapper.ProductMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ahto Jalak
 * @since 04.02.2023
 */
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService service;
    private final ProductMapper mapper;

    @GetMapping("/mylist")
    @PreAuthorize("hasRole('USER')")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<String> demo() {
        logger.info("demo request");
        return ResponseEntity.ok("Hello");
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<ProductDTO> add(@RequestBody ProductDTO request) {
        logger.info("add request");
        service.createProduct(mapper.mapToEntity(request));
        return ResponseEntity.ok(request);
    }
}
