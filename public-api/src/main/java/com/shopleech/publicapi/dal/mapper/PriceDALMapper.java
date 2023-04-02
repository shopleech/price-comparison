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

//    @Autowired
//    OfferDALMapper offerDALMapper;

    public List<PriceDALDTO> mapToDto(List<Price> prices) {
        return prices.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public PriceDALDTO mapToDto(Price c) {

        PriceDALDTO dto = new PriceDALDTO();
        dto.setId(c.getId());
        // offerDALMapper.mapToDto(c.getOffer()),
        dto.setPriceTypeCode(c.getPriceTypeCode());
        dto.setQuantity(c.getQuantity());
        dto.setAmount(c.getAmount());
        dto.setCurrency(c.getCurrency());

        return dto;
    }

    public List<Price> mapToEntity(List<PriceDALDTO> prices) {
        return prices.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Price mapToEntity(PriceDALDTO entity) {

        Price c = new Price();
        c.setId(entity.getId());
//        c.setOffer(offerDALMapper.mapToEntity(entity.getOffer()));
        c.setPriceTypeCode(entity.getPriceTypeCode());
        c.setQuantity(entity.getQuantity());
        c.setAmount(entity.getAmount());
        c.setCurrency(entity.getCurrency());

        return c;
    }
}
