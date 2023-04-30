package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.service.OfferService;
import com.shopleech.publicapi.bll.service.PriceService;
import com.shopleech.publicapi.dto.v1.OfferDTO;
import com.shopleech.publicapi.dto.v1.PriceDTO;
import com.shopleech.publicapi.dto.v1.mapper.OfferMapper;
import com.shopleech.publicapi.dto.v1.mapper.PriceMapper;
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
 * @since 01.04.2023
 */
@RestController
@RequestMapping("/v1/offer")
@Tag(name = "Offer controller", description = "Endpoint")
public class OfferController {
    Logger logger = LoggerFactory.getLogger(OfferController.class);

    @Autowired
    private OfferService offerService;
    @Autowired
    private PriceService priceService;
    @Autowired
    private OfferMapper offerMapper;
    @Autowired
    private PriceMapper priceMapper;

    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = offerService.getAll();
            responseMap.put("error", false);
            responseMap.put("details", offerMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getAllByProductId(@PathVariable(value = "id") Integer id) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var items = offerService.getAllByProductId(id);
            responseMap.put("error", false);
            responseMap.put("details", offerMapper.mapToDto(items));
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
            var item = offerService.get(id);
            responseMap.put("error", false);
            responseMap.put("details", offerMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody OfferDTO offerDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = offerService.add(
                    offerMapper.mapToEntity(offerDTO));
            var newPrice = new PriceDTO();
            newPrice.setOfferId(item.getId());
            newPrice.setAmount(offerDTO.getPrice().getAmount());
            var priceItem = priceService.add(
                    priceMapper.mapToEntity(newPrice));
            responseMap.put("error", false);
            responseMap.put("details", offerMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id, @RequestBody OfferDTO offerDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = offerService.update(id, offerMapper.mapToEntity(offerDTO));
            responseMap.put("error", false);
            responseMap.put("details", offerMapper.mapToDto(item));
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
            var item = offerService.remove(id);
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
