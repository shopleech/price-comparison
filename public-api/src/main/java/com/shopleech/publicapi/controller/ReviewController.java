package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.service.ProductService;
import com.shopleech.publicapi.bll.service.ReviewService;
import com.shopleech.publicapi.bll.service.UserService;
import com.shopleech.publicapi.domain.Review;
import com.shopleech.publicapi.dto.v1.ReviewDTO;
import com.shopleech.publicapi.dto.v1.mapper.ReviewMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ahto Jalak
 * @since 01.04.2023
 */
@RestController
@RequestMapping("/v1/review")
@Tag(name = "Review controller", description = "Endpoint")
public class ReviewController {
    Logger logger = LoggerFactory.getLogger(ReviewController.class);

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ReviewMapper reviewMapper;

    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = reviewService.getAll();
            responseMap.put("error", false);
            responseMap.put("details", reviewMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Integer id) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = reviewService.get(id);
            responseMap.put("error", false);
            responseMap.put("details", reviewMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody ReviewDTO reviewDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var user = userService.getCurrentUser();
            var item = new Review();
            item.setReviewTypeCode(reviewDTO.getReviewTypeCode());
            item.setCustomer(user.getCustomer());
            item.setProduct(productService.get(reviewDTO.getProductId()));
            item.setScore(reviewDTO.getScore());
            item.setDescription(reviewDTO.getDescription());

            responseMap.put("error", false);
            responseMap.put("details", reviewMapper.mapToDto(reviewService.add(item)));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id, @RequestBody ReviewDTO reviewDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = reviewService.update(id, reviewMapper.mapToEntity(reviewDTO));
            responseMap.put("error", false);
            responseMap.put("details", reviewMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable(value = "id") Integer id) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = reviewService.remove(id);
            responseMap.put("error", false);
            responseMap.put("details", item);
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }
}
