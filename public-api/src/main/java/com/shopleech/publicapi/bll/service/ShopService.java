package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.service.model.IShopService;
import com.shopleech.publicapi.dal.repository.ShopRepository;
import com.shopleech.publicapi.domain.Shop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 02.04.2023
 */
@Service
public class ShopService implements IShopService {
    Logger logger = LoggerFactory.getLogger(ShopService.class);

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public List<Shop> getAll() {
        return shopRepository.findAll();
    }

    @Override
    public Shop get(Integer id) throws Exception {
        var item = shopRepository.findById(id);

        if (item.isEmpty()) {
            throw new Exception("shop not found");
        }

        return item.get();
    }

    @Override
    public Shop add(Shop shop) throws Exception {
        if (shop.getName().trim().isEmpty()) {
            throw new Exception("shop name missing");
        }

        return shopRepository.save(shop);
    }

    @Override
    public Shop update(Integer id, Shop shop) throws Exception {
        var item = get(id);
        item.setName(shop.getName());
        item.setAddress(shop.getAddress());
        item.setUrl(shop.getUrl());
        item.setLatitude(shop.getLatitude());
        item.setLongitude(shop.getLongitude());

        return shopRepository.save(item);
    }

    @Override
    public Integer remove(Integer id) throws Exception {
        shopRepository.delete(get(id));

        if (get(id) != null) {
            throw new Exception("shop delete failed");
        }

        return id;
    }

    @Override
    public List<Shop> search(Shop entity) {
        return shopRepository.findByName(entity.getName());
    }
}
