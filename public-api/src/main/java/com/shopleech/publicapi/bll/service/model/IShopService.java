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

    Shop add(Shop shop) throws Exception;

    Shop update(Integer id, Shop shop) throws Exception;

    Integer remove(Integer id) throws Exception;

    List<Shop> search(Shop entity);
}
