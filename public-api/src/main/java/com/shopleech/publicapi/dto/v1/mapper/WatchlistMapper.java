package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.bll.service.ProductService;
import com.shopleech.publicapi.bll.service.UserService;
import com.shopleech.publicapi.domain.Watchlist;
import com.shopleech.publicapi.dto.v1.WatchlistDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class WatchlistMapper {
    Logger logger = LoggerFactory.getLogger(WatchlistMapper.class);

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    public List<WatchlistDTO> mapToDto(List<Watchlist> watchlists) {
        return watchlists.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public WatchlistDTO mapToDto(Watchlist c) {
        return new WatchlistDTO(
                c.getId(),
                c.getWatchlistTypeCode(),
                c.getProduct().getId()
        );
    }

    public Watchlist mapToEntity(WatchlistDTO newWatchlist) throws Exception {
        Watchlist entity = new Watchlist();
        entity.setId(newWatchlist.getId());
        entity.setCustomer(userService.getCurrentUser().getCustomer());
        entity.setProduct(productService.get(newWatchlist.getProductId()));
        entity.setWatchlistTypeCode(newWatchlist.getWatchlistTypeCode());

        return entity;
    }
}
