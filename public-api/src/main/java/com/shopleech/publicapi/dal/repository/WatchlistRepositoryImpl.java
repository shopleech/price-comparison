package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.WatchlistDALDTO;
import com.shopleech.publicapi.dal.mapper.WatchlistDALMapper;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
public class WatchlistRepositoryImpl implements WatchlistRepositoryCustom {

   private WatchlistRepository watchlistRepository;

   private WatchlistDALMapper watchlistDALMapper;

   @Override
   public void addWatchlist(WatchlistDALDTO watchlist) {
      // watchlistRepository.add(watchlistDALMapper.mapToEntity(watchlist));
   }

   @Override
   public WatchlistDALDTO getWatchlistById(Integer id) {
      return watchlistDALMapper.mapToDto(watchlistRepository.getReferenceById(id));
   }

   @Override
   public List<WatchlistDALDTO> getAllWatchlists() {
      return watchlistDALMapper.mapToDto(watchlistRepository.findAll());
   }
}
