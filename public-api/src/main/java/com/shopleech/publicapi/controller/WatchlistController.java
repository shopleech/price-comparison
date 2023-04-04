package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.service.WatchlistService;
import com.shopleech.publicapi.dto.v1.WatchlistDTO;
import com.shopleech.publicapi.dto.v1.mapper.WatchlistMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private WatchlistMapper watchlistMapper;

    @GetMapping("/{id}")
    public ResponseEntity<WatchlistDTO> getById(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(watchlistMapper.mapToDto(watchlistService.get(id)));
    }
}
