package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.service.ShopService;
import com.shopleech.publicapi.dto.v1.ShopDTO;
import com.shopleech.publicapi.dto.v1.mapper.ShopMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
 * @since 02.04.2023
 */
@RestController
@RequestMapping("/v1/shop")
@Tag(name = "Shop controller", description = "Endpoint")
public class ShopController {
    Logger logger = LoggerFactory.getLogger(ShopController.class);

    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopMapper shopMapper;

    @Operation(
            summary = "Shop list",
            responses = @ApiResponse(responseCode = "200", description = "Success"))
    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = shopService.getAll();
            responseMap.put("error", false);
            responseMap.put("details", shopMapper.mapToDto(item));
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
            var item = shopService.get(id);
            responseMap.put("error", false);
            responseMap.put("details", shopMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @Operation(
            summary = "Create shop",
            responses = @ApiResponse(responseCode = "200", description = "Shop returned"))
    @PostMapping
    public ResponseEntity<?> add(@RequestBody ShopDTO shopDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = shopService.add(shopMapper.mapToEntity(shopDTO));
            responseMap.put("error", false);
            responseMap.put("details", shopMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id, @RequestBody ShopDTO shopDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = shopService.update(id, shopMapper.mapToEntity(shopDTO));
            responseMap.put("error", false);
            responseMap.put("details", shopMapper.mapToDto(item));
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
            var item = shopService.remove(id);
            responseMap.put("error", false);
            responseMap.put("details", item);
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @Operation(
            summary = "Search shop",
            responses = @ApiResponse(responseCode = "200", description = "Shop returned"))
    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody ShopDTO shopDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = shopService.search(
                    shopMapper.mapToEntity(shopDTO));
            responseMap.put("error", false);
            responseMap.put("details", shopMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }
}
