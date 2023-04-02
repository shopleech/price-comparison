package com.shopleech.publicapi.dal.mapper;

import com.shopleech.publicapi.dal.dto.WatchlistDALDTO;
import com.shopleech.publicapi.domain.Watchlist;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class WatchlistDALMapper {

//    @Autowired
//    CustomerDALMapper customerDALMapper;
//    @Autowired
//    ProductDALMapper productDALMapper;

    public List<WatchlistDALDTO> mapToDto(List<Watchlist> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public WatchlistDALDTO mapToDto(Watchlist c) {

        WatchlistDALDTO dto = new WatchlistDALDTO();
        dto.setId(c.getId());
        // dto.setCustomer(customerDALMapper.mapToDto(c.getCustomer()));
        // dto.setProduct(productDALMapper.mapToDto(c.getProduct()));
        dto.setWatchlistTypeCode(c.getWatchlistTypeCode());

        return dto;
    }

    public List<Watchlist> mapToEntity(List<WatchlistDALDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Watchlist mapToEntity(WatchlistDALDTO entity) {

        Watchlist c = new Watchlist();
        c.setId(entity.getId());
//        c.setCustomer(customerDALMapper.mapToEntity(entity.getCustomer()));
//        c.setProduct(productDALMapper.mapToEntity(entity.getProduct()));
        c.setWatchlistTypeCode(entity.getWatchlistTypeCode());

        return c;
    }
}
