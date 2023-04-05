package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.service.model.IFeatureService;
import com.shopleech.publicapi.dal.repository.FeatureRepository;
import com.shopleech.publicapi.domain.Feature;
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
public class FeatureService implements IFeatureService {
    Logger logger = LoggerFactory.getLogger(FeatureService.class);

    @Autowired
    protected FeatureRepository featureRepository;

    @Override
    public Integer remove(Integer id) {
        return id;
    }

    @Override
    public Feature update(Integer id, Feature feature) {
        return feature;
    }

    @Override
    public Feature add(Feature feature) {
        return feature;
    }

    @Override
    public Feature get(Integer id) throws Exception {
        var item = featureRepository.findById(id);

        if (item.isEmpty()) {
            throw new Exception("feature not found");
        }

        return item.get();
    }

    @Override
    public List<Feature> getAll() {
        return featureRepository.findAll();
    }
}
