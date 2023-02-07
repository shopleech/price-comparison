package com.shopleech.publicapi.bll.mapper;

import com.shopleech.publicapi.bll.dto.WatchlistBLLDTO;
import com.shopleech.publicapi.dal.dto.WatchlistDALDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Component
public class WatchlistBLLMapper {

   public List<WatchlistBLLDTO> mapToDto(List<WatchlistDALDTO> accounts) {
      return accounts.stream()
              .map(this::mapToDto).collect(Collectors.toList());
   }

   public WatchlistBLLDTO mapToDto(WatchlistDALDTO c) {
      return new WatchlistBLLDTO(
              c.getId(),
              c.getWatchTypeCode()
      );
   }

   public WatchlistDALDTO mapToEntity(WatchlistBLLDTO newWatchlist) {
      WatchlistDALDTO entity = new WatchlistDALDTO();
      entity.setId(newWatchlist.getId());
      entity.setWatchTypeCode(newWatchlist.getWatchTypeCode());
      return entity;
   }

}
