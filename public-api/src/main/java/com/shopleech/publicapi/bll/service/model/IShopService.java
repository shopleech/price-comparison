package com.shopleech.publicapi.bll.service.model;

import com.shopleech.publicapi.domain.Shop;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 05.04.2023
 */
public interface IShopService {
    List<Shop> getAll();

    Shop get(Integer id) throws Exception;

    Shop add(Shop shop);

    Shop update(Integer id, Shop shop);

    Integer remove(Integer id);
}
