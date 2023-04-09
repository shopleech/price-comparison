package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.service.MetricService;
import com.shopleech.publicapi.dto.v1.MetricDTO;
import com.shopleech.publicapi.dto.v1.mapper.MetricMapper;
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
@RequestMapping("/v1/metric")
@Tag(name = "Metric controller", description = "Endpoint")
public class MetricController {
    Logger logger = LoggerFactory.getLogger(MetricController.class);

    @Autowired
    private MetricService metricService;
    @Autowired
    private MetricMapper metricMapper;

    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = metricService.getAll();
            responseMap.put("error", false);
            responseMap.put("details", metricMapper.mapToDto(item));
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
            var item = metricService.get(id);
            responseMap.put("error", false);
            responseMap.put("details", metricMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody MetricDTO metricDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = metricService.add(metricMapper.mapToEntity(metricDTO));
            responseMap.put("error", false);
            responseMap.put("details", metricMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id, @RequestBody MetricDTO metricDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = metricService.update(id, metricMapper.mapToEntity(metricDTO));
            responseMap.put("error", false);
            responseMap.put("details", metricMapper.mapToDto(item));
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
            var item = metricService.remove(id);
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
