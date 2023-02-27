package com.shopleech.publicapi.dal.mapper;

import com.shopleech.publicapi.dal.dto.WatchlistDALDTO;
import com.shopleech.publicapi.domain.Watchlist;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class WatchlistDALMapper {

    public List<WatchlistDALDTO> mapToDto(List<Watchlist> watchlists) {
        return watchlists.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public WatchlistDALDTO mapToDto(Watchlist c) {
        return new WatchlistDALDTO(
                c.getId(),
                c.getWatchTypeCode()
        );
    }

    public Watchlist mapToEntity(WatchlistDALDTO newWatchlist) {
        Watchlist entity = new Watchlist();
        entity.setId(newWatchlist.getId());
        entity.setWatchTypeCode(newWatchlist.getWatchTypeCode());
        return entity;
    }

}
