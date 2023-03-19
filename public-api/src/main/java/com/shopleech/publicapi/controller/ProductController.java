package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.service.ProductService;
import com.shopleech.publicapi.dto.v1.ProductDTO;
import com.shopleech.publicapi.dto.v1.ProductSearchDTO;
import com.shopleech.publicapi.dto.v1.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ahto Jalak
 * @since 04.02.2023
 */
@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {

    Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;
    private final ProductMapper mapper;

    @GetMapping("/mylist")
    public ResponseEntity<?> demo() {
        logger.info("demo request");

        Map<String, Object> responseMap = new HashMap<>();
        try {
            var serviceAll = productService.getAll();
            responseMap.put("error", false);
            responseMap.put("details", mapper.mapToDto(serviceAll));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @GetMapping("/something")
    public ResponseEntity<?> something() {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("something", true);
        return ResponseEntity.ok(responseMap);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductDTO> add(@RequestBody ProductDTO request) {
        logger.info("add request");
        productService.createProduct(mapper.mapToEntity(request));
        return ResponseEntity.ok(request);
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody ProductSearchDTO request) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var serviceAll = productService.getAllByKeyword(request.getKeyword());
            responseMap.put("error", false);
            responseMap.put("details", mapper.mapToDto(serviceAll));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }
}
