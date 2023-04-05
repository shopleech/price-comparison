package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.domain.Price;
import com.shopleech.publicapi.dto.v1.PriceDTO;
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
public class PriceMapper {
    Logger logger = LoggerFactory.getLogger(PriceMapper.class);

    public List<PriceDTO> mapToDto(List<Price> prices) {
        return prices.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public PriceDTO mapToDto(Price c) {
        return new PriceDTO(
                c.getId(),
                c.getPriceTypeCode(),
                c.getQuantity(),
                c.getAmount(),
                c.getCurrency()
        );
    }

    public Price mapToEntity(PriceDTO newPrice) {
        Price entity = new Price();
        entity.setId(newPrice.getId());
        entity.setPriceTypeCode(newPrice.getPriceTypeCode());
        entity.setQuantity(newPrice.getQuantity());
        entity.setAmount(newPrice.getAmount());
        entity.setCurrency(newPrice.getCurrency());
        return entity;
    }

}
