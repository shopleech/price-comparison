package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.bll.dto.PriceBLLDTO;
import com.shopleech.publicapi.dto.v1.PriceDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class PriceMapper {

    public List<PriceDTO> mapToDto(List<PriceBLLDTO> prices) {
        return prices.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public PriceDTO mapToDto(PriceBLLDTO c) {
        return new PriceDTO(
                c.getId(),
                c.getPriceTypeCode(),
                c.getQuantity(),
                c.getAmount(),
                c.getCurrency()
        );
    }

    public PriceBLLDTO mapToEntity(PriceDTO newPrice) {
        PriceBLLDTO entity = new PriceBLLDTO();
        entity.setId(newPrice.getId());
        entity.setPriceTypeCode(newPrice.getPriceTypeCode());
        entity.setQuantity(newPrice.getQuantity());
        entity.setAmount(newPrice.getAmount());
        entity.setCurrency(newPrice.getCurrency());
        return entity;
    }

}
