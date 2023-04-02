package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.service.ProductService;
import com.shopleech.publicapi.dto.v1.ProductDTO;
import com.shopleech.publicapi.dto.v1.ProductImportDTO;
import com.shopleech.publicapi.dto.v1.ProductSearchDTO;
import com.shopleech.publicapi.dto.v1.mapper.ProductMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Product controller", description = "Endpoint")
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

    @PostMapping("/import")
    public ResponseEntity<?> add(@RequestHeader("Authorization") String token, @RequestBody ProductImportDTO request) {
        logger.info("add import");

        String jwt = token.substring(7);

        Map<String, Object> responseMap = new HashMap<>();
        try {
            var productsImported = productService.importProducts(jwt, request.getProductImportItems());
            if (productsImported != null) {
                responseMap.put("error", false);
                responseMap.put("message", "import_success");
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("error", true);
                responseMap.put("message", "invalid_input");
                return ResponseEntity.status(401).body(responseMap);
            }
        } catch (Exception e) {
            responseMap.put("error", true);
            if ("consent is missing".equals(e.getMessage())) {
                responseMap.put("message", "consent_is_missing");
                responseMap.put("message_meta", e.getMessage());
            } else {
                responseMap.put("message", "something_went_wrong");
                responseMap.put("message_meta", e.getMessage());
            }
            return ResponseEntity.status(500).body(responseMap);
        }


    }
}
