package com.shopleech.publicapi.bll.mapper;

import com.shopleech.publicapi.bll.dto.PriceBLLDTO;
import com.shopleech.publicapi.dal.dto.PriceDALDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 23.03.2023
 */
@Component
public class PriceBLLMapper {

//    @Autowired
//    OfferBLLMapper offerBLLMapper;

    public List<PriceBLLDTO> mapToDto(List<PriceDALDTO> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public PriceBLLDTO mapToDto(PriceDALDTO c) {
        PriceBLLDTO dto = new PriceBLLDTO();
        dto.setId(c.getId());
        // offerBLLMapper.mapToDto(c.getOffer()),
        dto.setPriceTypeCode(c.getPriceTypeCode());
        dto.setQuantity(c.getQuantity());
        dto.setAmount(c.getAmount());
        dto.setCurrency(c.getCurrency());

        return dto;
    }

    public List<PriceDALDTO> mapToEntity(List<PriceBLLDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public PriceDALDTO mapToEntity(PriceBLLDTO entity) {

        PriceDALDTO c = new PriceDALDTO();
        c.setId(entity.getId());
        // c.setOffer(offerBLLMapper.mapToEntity(entity.getOffer()));
        c.setPriceTypeCode(entity.getPriceTypeCode());
        c.setQuantity(entity.getQuantity());
        c.setAmount(entity.getAmount());
        c.setCurrency(entity.getCurrency());

        return c;
    }
}
