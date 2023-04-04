package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.service.CategoryService;
import com.shopleech.publicapi.dto.v1.CategoryDTO;
import com.shopleech.publicapi.dto.v1.mapper.CategoryMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ahto Jalak
 * @since 01.04.2023
 */
@RestController
@RequestMapping("/v1/cateogry")
@Tag(name = "Category controller", description = "Endpoint")
public class CategoryController {
    Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<?> getAll() {
        logger.info("category all request");

        Map<String, Object> responseMap = new HashMap<>();
        try {
            var serviceAll = categoryService.getAll();
            responseMap.put("error", false);
            responseMap.put("details", categoryMapper.mapToDto(serviceAll));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(categoryMapper.mapToDto(categoryService.get(id)));
    }
}
