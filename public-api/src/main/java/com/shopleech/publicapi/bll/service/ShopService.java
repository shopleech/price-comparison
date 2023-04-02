package com.shopleech.publicapi.bll.service;

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
public class ShopService {
    Logger logger = LoggerFactory.getLogger(ShopService.class);

    @Autowired
    private ShopRepository shopRepository;

    public Shop createNewShop(Shop shop) {
        logger.info(shop.getName());
        return shopRepository.save(shop);
    }

    public List<Shop> findShops() {
        return shopRepository.findAll();
    }
}
