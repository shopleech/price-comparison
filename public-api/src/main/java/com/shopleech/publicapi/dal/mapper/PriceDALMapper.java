package com.shopleech.publicapi.dal.mapper;

import com.shopleech.publicapi.dal.dto.PriceDALDTO;
import com.shopleech.publicapi.domain.Price;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class PriceDALMapper {

   public List<PriceDALDTO> mapToDto(List<Price> prices) {
      return prices.stream()
              .map(this::mapToDto).collect(Collectors.toList());
   }

   public PriceDALDTO mapToDto(Price c) {
      return new PriceDALDTO(
              c.getId(),
              c.getPriceTypeCode(),
              c.getQuantity(),
              c.getAmount(),
              c.getCurrency()
      );
   }

   public Price mapToEntity(PriceDALDTO newPrice) {
      Price entity = new Price();
      entity.setId(newPrice.getId());
      entity.setPriceTypeCode(newPrice.getPriceTypeCode());
      entity.setQuantity(newPrice.getQuantity());
      entity.setAmount(newPrice.getAmount());
      entity.setCurrency(newPrice.getCurrency());
      return entity;
   }

}
