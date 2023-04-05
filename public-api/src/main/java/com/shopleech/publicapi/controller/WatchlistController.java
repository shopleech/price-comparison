package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.service.CustomerService;
import com.shopleech.publicapi.bll.service.ProductService;
import com.shopleech.publicapi.bll.service.UserService;
import com.shopleech.publicapi.bll.service.WatchlistService;
import com.shopleech.publicapi.domain.Watchlist;
import com.shopleech.publicapi.dto.v1.WatchlistDTO;
import com.shopleech.publicapi.dto.v1.mapper.WatchlistMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@RestController
@RequestMapping("/v1/watchlist")
@Tag(name = "Watchlist controller", description = "Endpoint")
public class WatchlistController {
    Logger logger = LoggerFactory.getLogger(WatchlistController.class);

    @Autowired
    private WatchlistService watchlistService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;
    @Autowired
    private WatchlistMapper watchlistMapper;

    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = watchlistService.getAll();
            responseMap.put("error", false);
            responseMap.put("details", watchlistMapper.mapToDto(item));
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
            var item = watchlistService.get(id);
            responseMap.put("error", false);
            responseMap.put("details", watchlistMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody WatchlistDTO watchlistDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var user = userService.getCurrentUser();
            var watchlist = new Watchlist();
            watchlist.setWatchlistTypeCode(watchlistDTO.getWatchlistTypeCode());
            watchlist.setCustomer(user.getCustomer());
            watchlist.setProduct(productService.get(watchlistDTO.getProductId()));

            var item = watchlistService.add(watchlist);
            responseMap.put("error", false);
            responseMap.put("details", watchlistMapper.mapToDto(item));

            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            logger.info(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id, @RequestBody WatchlistDTO watchlistDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = watchlistService.update(id, watchlistMapper.mapToEntity(watchlistDTO));
            responseMap.put("error", false);
            responseMap.put("details", watchlistMapper.mapToDto(item));
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
            var item = watchlistService.remove(id);
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
