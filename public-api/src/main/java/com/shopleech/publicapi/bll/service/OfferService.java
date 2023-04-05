package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.service.model.IOfferService;
import com.shopleech.publicapi.dal.repository.OfferRepository;
import com.shopleech.publicapi.domain.Offer;
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
public class OfferService implements IOfferService {
    Logger logger = LoggerFactory.getLogger(OfferService.class);

    @Autowired
    protected OfferRepository offerRepository;

    @Override
    public List<Offer> getAll() {
        return offerRepository.findAll();
    }

    @Override
    public Offer get(Integer id) throws Exception {
        var item = offerRepository.findById(id);

        if (item.isEmpty()) {
            throw new Exception("offer not found");
        }

        return item.get();
    }

    @Override
    public Offer add(Offer offer) {
        return offer;
    }

    @Override
    public Offer update(Integer id, Offer offer) {
        return offer;
    }

    @Override
    public Integer remove(Integer id) {
        return id;
    }
}
