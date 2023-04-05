package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.domain.Offer;
import com.shopleech.publicapi.dto.v1.OfferDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 05.04.2023
 */
@Component
public class OfferMapper {
    Logger logger = LoggerFactory.getLogger(OfferMapper.class);

    public List<OfferDTO> mapToDto(List<Offer> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public OfferDTO mapToDto(Offer c) {
        OfferDTO dto = new OfferDTO();
        dto.setId(c.getId());
        // dto.setParentOffer();
        //dto.setName(c.getName());
        // dto.setOfferTypeCode();

        return dto;
    }

    public List<Offer> mapToEntity(List<OfferDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Offer mapToEntity(OfferDTO entity) {

        Offer c = new Offer();
        c.setId(entity.getId());
        // c.setParentOffer();
        //c.setName(entity.getName());
        // c.setOfferTypeCode();

        return c;
    }
}
