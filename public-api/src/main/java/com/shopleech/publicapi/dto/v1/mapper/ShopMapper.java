package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.domain.Shop;
import com.shopleech.publicapi.dto.v1.ShopDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 02.04.2023
 */
@Component
public class ShopMapper {
    Logger logger = LoggerFactory.getLogger(ShopMapper.class);

    public List<ShopDTO> mapToDto(List<Shop> shops) {
        return shops.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public ShopDTO mapToDto(Shop c) {
        logger.info("map-to-dto");
        logger.info(c.getName());
        return new ShopDTO(
                c.getId(),
                c.getName(),
                c.getAddress(),
                c.getUrl(),
                c.getLatitude(),
                c.getLongitude()
        );
    }

    public List<Shop> mapToEntity(List<ShopDTO> shops) {
        return shops.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Shop mapToEntity(ShopDTO s) {
        Shop entity = new Shop();
        entity.setId(s.getId());
        entity.setName(s.getName());
        entity.setAddress(s.getAddress());
        entity.setUrl(s.getUrl());
        entity.setLatitude(s.getLatitude());
        entity.setLongitude(s.getLongitude());
        return entity;
    }
}
