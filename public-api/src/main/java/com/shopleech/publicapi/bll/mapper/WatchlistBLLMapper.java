package com.shopleech.publicapi.bll.mapper;

import com.shopleech.publicapi.bll.dto.WatchlistBLLDTO;
import com.shopleech.publicapi.dal.dto.WatchlistDALDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Component
public class WatchlistBLLMapper {

//    @Autowired
//    CustomerBLLMapper customerBLLMapper;
//    @Autowired
//    ProductBLLMapper productBLLMapper;

    public List<WatchlistBLLDTO> mapToDto(List<WatchlistDALDTO> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public WatchlistBLLDTO mapToDto(WatchlistDALDTO c) {

        WatchlistBLLDTO dto = new WatchlistBLLDTO();
        dto.setId(c.getId());
        // customerBLLMapper.mapToDto(c.getCustomer()),
        // productBLLMapper.mapToDto(c.getProduct()),
        dto.setWatchlistTypeCode(c.getWatchlistTypeCode());

        return dto;
    }

    public List<WatchlistDALDTO> mapToEntity(List<WatchlistBLLDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public WatchlistDALDTO mapToEntity(WatchlistBLLDTO entity) {

        WatchlistDALDTO c = new WatchlistDALDTO();
        c.setId(entity.getId());
//        c.setCustomer(customerBLLMapper.mapToEntity(entity.getCustomer()));
//        c.setProduct(productBLLMapper.mapToEntity(entity.getProduct()));
        c.setWatchlistTypeCode(entity.getWatchlistTypeCode());

        return c;
    }
}
