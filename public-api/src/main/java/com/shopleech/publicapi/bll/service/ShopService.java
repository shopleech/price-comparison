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

    public Shop createNewShop(Shop shop) {
        logger.info(shop.getName());
        return shopRepository.save(shop);
    }

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
    public Shop add(Shop shop) {
        return shop;
    }

    @Override
    public Shop update(Integer id, Shop shop) {
        return shop;
    }

    @Override
    public Integer remove(Integer id) {
        return id;
    }
}
