package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.PriceDALDTO;
import com.shopleech.publicapi.dal.mapper.PriceDALMapper;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
public class PriceRepositoryImpl implements PriceRepositoryCustom {

   private PriceRepository priceRepository;

   private PriceDALMapper mapper;

   @Override
   public void addPrice(PriceDALDTO price) {
      // priceRepository.add(mapper.mapToEntity(price));
   }

   @Override
   public PriceDALDTO getPriceById(Integer id) {
      return mapper.mapToDto(priceRepository.getReferenceById(id));
   }

   @Override
   public List<PriceDALDTO> getAllPrices() {
      return mapper.mapToDto(priceRepository.findAll());
   }
}
