package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.service.model.IOfferService;
import com.shopleech.publicapi.dal.repository.CategoryRepository;
import com.shopleech.publicapi.dal.repository.OfferRepository;
import com.shopleech.publicapi.dal.repository.ProductRepository;
import com.shopleech.publicapi.domain.Offer;
import com.shopleech.publicapi.domain.Product;
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
    @Autowired
    protected ProductRepository productRepository;
    @Autowired
    protected CategoryRepository categoryRepository;

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
    public Offer add(Offer entity) throws Exception {
        if (entity.getProduct() == null) {
            var product = productRepository.findByBarcode(entity.getBarcode());
            if (product == null) {
                var item = new Product();
                item.setBarcode(entity.getBarcode());
                item.setName(entity.getName());
                var category = categoryRepository.findById(10);
                if (category.isPresent()) {
                    item.setCategory(category.get());
                }
                product = productRepository.save(item);
            }
            entity.setProduct(product);
        }

        var offer = offerRepository.findByProduct(
                entity.getAccount().getId(),
                entity.getProduct().getId(),
                entity.getShop().getId()
        );

        if (offer != null) {
            return offer;
        }

        return offerRepository.save(entity);
    }

    @Override
    public Offer update(Integer id, Offer offer) {
        return offer;
    }

    @Override
    public Integer remove(Integer id) {
        return id;
    }

    @Override
    public List<Offer> getAllByProductId(Integer id) {
        return offerRepository.getAllByProductId(id);
    }
}
