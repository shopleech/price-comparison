package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.service.RoleService;
import com.shopleech.publicapi.bll.service.ShopService;
import com.shopleech.publicapi.domain.Role;
import com.shopleech.publicapi.dto.v1.RoleDTO;
import com.shopleech.publicapi.dto.v1.mapper.RoleMapper;
import com.shopleech.publicapi.dto.v1.mapper.ShopMapper;
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
 * @since 25.02.2023
 */
@RestController
@RequestMapping("/v1/role")
@Tag(name = "Role controller", description = "Endpoint")
public class RoleController {
    Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMapper roleMapper;

    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = roleService.getAll();
            responseMap.put("error", false);
            responseMap.put("details", roleMapper.mapToDto(item));
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
            var item = roleService.get(id);
            responseMap.put("error", false);
            responseMap.put("details", roleMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody RoleDTO roleDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = roleService.add(roleMapper.mapToEntity(roleDTO));
            responseMap.put("error", false);
            responseMap.put("details", roleMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id, @RequestBody RoleDTO roleDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = roleService.update(id, roleMapper.mapToEntity(roleDTO));
            responseMap.put("error", false);
            responseMap.put("details", roleMapper.mapToDto(item));
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
            var item = roleService.remove(id);
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
