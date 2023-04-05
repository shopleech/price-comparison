package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.service.model.IPriceService;
import com.shopleech.publicapi.dal.repository.PriceRepository;
import com.shopleech.publicapi.domain.Price;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 05.04.2023
 */
@Service
public class PriceService implements IPriceService {
    Logger logger = LoggerFactory.getLogger(PriceService.class);

    @Autowired
    protected PriceRepository priceRepository;

    @Override
    public List<Price> getAll() {
        return priceRepository.findAll();
    }

    @Override
    public Price get(Integer id) throws Exception {
        var item = priceRepository.findById(id);

        if (item.isEmpty()) {
            throw new Exception("price not found");
        }

        return item.get();
    }

    @Override
    public Price add(Price price) {
        return price;
    }

    @Override
    public Price update(Integer id, Price price) {
        return price;
    }

    @Override
    public Integer remove(Integer id) {
        return id;
    }
}
