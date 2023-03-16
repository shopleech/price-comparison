package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.service.WatchlistService;
import com.shopleech.publicapi.dto.v1.WatchlistDTO;
import com.shopleech.publicapi.dto.v1.mapper.WatchlistMapper;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class WatchlistController {

    private WatchlistService watchlistService;

    private WatchlistMapper watchlistMapper;

    @GetMapping("/{id}")
    public ResponseEntity<WatchlistDTO> getById(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(watchlistMapper.mapToDto(watchlistService.get(id)));
    }
}