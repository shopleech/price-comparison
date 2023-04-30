package com.shopleech.publicapi.bll.service.model;

import com.shopleech.publicapi.domain.Watchlist;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 05.04.2023
 */
public interface IWatchlistService {
    Watchlist add(Watchlist data) throws Exception;

    Watchlist get(Integer id) throws Exception;

    List<Watchlist> getAll();

    Integer remove(Integer id) throws Exception;

    Watchlist update(Integer id, Watchlist newItem) throws Exception;
}
