package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.bll.dto.WatchlistBLLDTO;
import com.shopleech.publicapi.dto.v1.WatchlistDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class WatchlistMapper {
    Logger logger = LoggerFactory.getLogger(WatchlistMapper.class);

    public List<WatchlistDTO> mapToDto(List<WatchlistBLLDTO> watchlists) {
        return watchlists.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public WatchlistDTO mapToDto(WatchlistBLLDTO c) {
        return new WatchlistDTO(
                c.getId(),
                c.getWatchlistTypeCode()
        );
    }

    public WatchlistBLLDTO mapToEntity(WatchlistDTO newWatchlist) {
        WatchlistBLLDTO entity = new WatchlistBLLDTO();
        entity.setId(newWatchlist.getId());
        entity.setWatchlistTypeCode(newWatchlist.getWatchlistTypeCode());
        return entity;
    }

}
