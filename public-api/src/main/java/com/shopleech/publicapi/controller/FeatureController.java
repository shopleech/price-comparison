package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.service.FeatureService;
import com.shopleech.publicapi.dto.v1.FeatureDTO;
import com.shopleech.publicapi.dto.v1.mapper.FeatureMapper;
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
 * @since 05.04.2023
 */
@RestController
@RequestMapping("/v1/feature")
@Tag(name = "Feature controller", description = "Endpoint")
public class FeatureController {
    Logger logger = LoggerFactory.getLogger(FeatureController.class);

    @Autowired
    private FeatureService featureService;
    @Autowired
    private FeatureMapper featureMapper;
    
    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = featureService.getAll();
            responseMap.put("error", false);
            responseMap.put("details", featureMapper.mapToDto(item));
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
            var item = featureService.get(id);
            responseMap.put("error", false);
            responseMap.put("details", featureMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody FeatureDTO featureDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = featureService.add(featureMapper.mapToEntity(featureDTO));
            responseMap.put("error", false);
            responseMap.put("details", featureMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id, @RequestBody FeatureDTO featureDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = featureService.update(id, featureMapper.mapToEntity(featureDTO));
            responseMap.put("error", false);
            responseMap.put("details", featureMapper.mapToDto(item));
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
            var item = featureService.remove(id);
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
