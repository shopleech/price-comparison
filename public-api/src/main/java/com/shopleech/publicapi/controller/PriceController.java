package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.service.PriceService;
import com.shopleech.publicapi.dto.v1.PriceDTO;
import com.shopleech.publicapi.dto.v1.mapper.PriceMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/v1/price")
@Tag(name = "Price controller", description = "Endpoint")
public class PriceController {
    @Autowired
    private PriceService priceService;
    @Autowired
    private PriceMapper priceMapper;
    
    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = priceService.getAll();
            responseMap.put("error", false);
            responseMap.put("details", priceMapper.mapToDto(item));
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
            var item = priceService.get(id);
            responseMap.put("error", false);
            responseMap.put("details", priceMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody PriceDTO priceDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = priceService.add(priceMapper.mapToEntity(priceDTO));
            responseMap.put("error", false);
            responseMap.put("details", priceMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id, @RequestBody PriceDTO priceDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = priceService.update(id, priceMapper.mapToEntity(priceDTO));
            responseMap.put("error", false);
            responseMap.put("details", priceMapper.mapToDto(item));
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
            var item = priceService.remove(id);
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
