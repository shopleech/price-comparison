package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.bll.dto.StatsBLLDTO;
import com.shopleech.publicapi.dto.v1.StatsDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 22.03.2023
 */
@Component
public class StatsMapper {

    public List<StatsDTO> mapToDto(List<StatsBLLDTO> Stats) {
        return Stats.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public StatsDTO mapToDto(StatsBLLDTO c) {
        return new StatsDTO(
                c.getNumOfProducts(),
                c.getNumOfPriceUpdates(),
                c.getNumOfUsers()
        );
    }

    public StatsBLLDTO mapToEntity(StatsDTO newStats) {
        StatsBLLDTO entity = new StatsBLLDTO();
        entity.setNumOfProducts(newStats.getNumOfProducts());
        entity.setNumOfPriceUpdates(newStats.getNumOfPriceUpdates());
        entity.setNumOfUsers(newStats.getNumOfUsers());
        return entity;
    }
}
