package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.dto.WatchlistBLLDTO;
import com.shopleech.publicapi.bll.mapper.WatchlistBLLMapper;
import com.shopleech.publicapi.dal.repository.WatchlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Service
@RequiredArgsConstructor
public class WatchlistService {

   protected WatchlistRepository watchlistRepository;

   protected WatchlistBLLMapper watchlistMapper;

   public void createWatchlist(WatchlistBLLDTO data) {
      watchlistRepository.addWatchlist(watchlistMapper.mapToEntity(data));
   }

   public WatchlistBLLDTO get(Integer id) {
      return watchlistMapper.mapToDto(watchlistRepository.getWatchlistById(id));
   }

   public List<WatchlistBLLDTO> getAll() {
      return watchlistMapper.mapToDto(watchlistRepository.getAllWatchlists());
   }
}
