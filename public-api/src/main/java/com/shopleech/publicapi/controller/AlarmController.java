package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.service.AlarmService;
import com.shopleech.publicapi.dto.v1.AlarmDTO;
import com.shopleech.publicapi.dto.v1.mapper.AlarmMapper;
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
 * @since 06.02.2023
 */
@RestController
@RequestMapping("/v1/alarm")
@Tag(name = "Alarm controller", description = "Endpoint")
public class AlarmController {
    Logger logger = LoggerFactory.getLogger(AlarmController.class);

    @Autowired
    private AlarmService alarmService;
    @Autowired
    private AlarmMapper alarmMapper;

    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            responseMap.put("error", false);
            responseMap.put("details", alarmMapper.mapToDto(alarmService.getAll()));
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
            responseMap.put("error", false);
            responseMap.put("details", alarmMapper.mapToDto(alarmService.get(id)));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody AlarmDTO alarmDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            responseMap.put("error", false);
            responseMap.put("details", alarmMapper.mapToDto(
                    alarmService.add(alarmMapper.mapToEntity(alarmDTO))));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id, @RequestBody AlarmDTO alarmDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            responseMap.put("error", false);
            responseMap.put("details", alarmMapper.mapToDto(
                    alarmService.update(id, alarmMapper.mapToEntity(alarmDTO))));
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
            responseMap.put("error", false);
            responseMap.put("details", alarmService.remove(id));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }
}
