package com.shopleech.publicapi.dal.mapper;

import com.shopleech.publicapi.dal.dto.ShopDALDTO;
import com.shopleech.publicapi.domain.Shop;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 25.03.2023
 */
@Component
public class ShopDALMapper {

    public List<ShopDALDTO> mapToDto(List<Shop> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public ShopDALDTO mapToDto(Shop c) {

        ShopDALDTO dto = new ShopDALDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setAddress(c.getAddress());
        dto.setUrl(c.getUrl());
        dto.setLatitude(c.getLatitude());
        dto.setLongitude(c.getLongitude());

        return dto;
    }

    public List<Shop> mapToEntity(List<ShopDALDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Shop mapToEntity(ShopDALDTO entity) {

        Shop c = new Shop();
        c.setId(entity.getId());
        c.setName(entity.getName());
        c.setAddress(entity.getAddress());
        c.setUrl(entity.getUrl());
        c.setLatitude(entity.getLatitude());
        c.setLongitude(entity.getLongitude());

        return c;
    }
}
