package com.shopleech.publicapi.bll.mapper;

import com.shopleech.publicapi.bll.dto.ShopBLLDTO;
import com.shopleech.publicapi.dal.dto.ShopDALDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 25.03.2023
 */
@Component
public class ShopBLLMapper {

    public List<ShopBLLDTO> mapToDto(List<ShopDALDTO> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public ShopBLLDTO mapToDto(ShopDALDTO c) {
        ShopBLLDTO dto = new ShopBLLDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setAddress(c.getAddress());
        dto.setUrl(c.getUrl());
        dto.setLatitude(c.getLatitude());
        dto.setLongitude(c.getLongitude());

        return dto;
    }

    public List<ShopDALDTO> mapToEntity(List<ShopBLLDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public ShopDALDTO mapToEntity(ShopBLLDTO entity) {

        ShopDALDTO c = new ShopDALDTO();
        c.setId(entity.getId());
        c.setName(entity.getName());
        c.setAddress(entity.getAddress());
        c.setUrl(entity.getUrl());
        c.setLatitude(entity.getLatitude());
        c.setLongitude(entity.getLongitude());

        return c;
    }
}
