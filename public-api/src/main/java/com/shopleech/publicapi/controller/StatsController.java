package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.service.StatsService;
import com.shopleech.publicapi.dto.v1.mapper.StatsMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ahto Jalak
 * @since 22.03.2023
 */
@RestController
@RequestMapping("/v1/stats")
@Tag(name = "Stats Controller", description = "Endpoint for user access")
public class StatsController {
    Logger logger = LoggerFactory.getLogger(StatsController.class);

    @Autowired
    private StatsMapper statsMapper;
    @Autowired
    private StatsService statsService;

    @Operation(
            summary = "Public stats",
            responses = @ApiResponse(responseCode = "200", description = "Success"))
    @GetMapping("/public")
    public ResponseEntity<?> getPublicStats() {
        logger.info("user list request");

        Map<String, Object> responseMap = new HashMap<>();
        try {
            var stats = statsService.getPublicStats();
            responseMap.put("details", statsMapper.mapToDto(stats));
            responseMap.put("error", false);
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }
}
