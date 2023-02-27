package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.WatchlistDALDTO;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
public interface WatchlistRepositoryCustom {

    void addWatchlist(WatchlistDALDTO mapToEntity);

    WatchlistDALDTO getWatchlistById(Integer id);

    List<WatchlistDALDTO> getAllWatchlists();
}
