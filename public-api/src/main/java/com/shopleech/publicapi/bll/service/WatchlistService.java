package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.dal.repository.WatchlistRepository;
import com.shopleech.publicapi.domain.Watchlist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Service
public class WatchlistService {
    Logger logger = LoggerFactory.getLogger(WatchlistService.class);

    @Autowired
    protected WatchlistRepository watchlistRepository;

    public Watchlist add(Watchlist data) {
        return watchlistRepository.save(data);
    }

    public Watchlist get(Integer id) throws Exception {
        var item = watchlistRepository.findById(id);

        if (item.isEmpty()) {
            throw new Exception("alarm not found");
        }

        return item.get();
    }

    public List<Watchlist> getAll() {
        return watchlistRepository.findAll();
    }
}
