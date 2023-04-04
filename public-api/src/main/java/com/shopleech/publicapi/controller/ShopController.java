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
import java.util.Objects;

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
            summary = "Create shop",
            responses = @ApiResponse(responseCode = "200", description = "Shop returned"))
    @PostMapping
    public ResponseEntity<?> createNewShop(@RequestBody ShopDTO payload) {
        logger.info("asd" + Objects.requireNonNull(payload));

        Map<String, Object> responseMap = new HashMap<>();
        try {
            var shop = shopService.createNewShop(
                    shopMapper.mapToEntity(payload)
            );
            responseMap.put("details", shopMapper.mapToDto(shop));
            responseMap.put("error", false);
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }

    }

    @Operation(
            summary = "Shop list",
            responses = @ApiResponse(responseCode = "200", description = "Success"))
    @GetMapping
    public ResponseEntity<?> getShopList() {
        logger.info("shop list request");

        Map<String, Object> responseMap = new HashMap<>();
        try {
            var shops = shopMapper.mapToDto(shopService.findShops());
            responseMap.put("details", shops);
            responseMap.put("error", false);
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }
}
