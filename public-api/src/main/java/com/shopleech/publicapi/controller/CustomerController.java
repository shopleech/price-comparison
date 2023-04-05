package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.service.CustomerService;
import com.shopleech.publicapi.dto.v1.CustomerDTO;
import com.shopleech.publicapi.dto.v1.mapper.CustomerMapper;
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
@RequestMapping("/v1/customer")
@Tag(name = "Customer controller", description = "Endpoint")
public class CustomerController {
    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerMapper customerMapper;

    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = customerService.getAll();
            responseMap.put("error", false);
            responseMap.put("details", customerMapper.mapToDto(item));
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
            var item = customerService.get(id);
            responseMap.put("error", false);
            responseMap.put("details", customerMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody CustomerDTO customerDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = customerService.add(customerMapper.mapToEntity(customerDTO));
            responseMap.put("error", false);
            responseMap.put("details", customerMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id, @RequestBody CustomerDTO customerDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = customerService.update(id, customerMapper.mapToEntity(customerDTO));
            responseMap.put("error", false);
            responseMap.put("details", customerMapper.mapToDto(item));
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
            var item = customerService.remove(id);
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
