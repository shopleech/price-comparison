package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.dto.StatsBLLDTO;
import com.shopleech.publicapi.bll.mapper.ProductBLLMapper;
import com.shopleech.publicapi.bll.mapper.UserBLLMapper;
import com.shopleech.publicapi.dal.repository.ProductRepository;
import com.shopleech.publicapi.dal.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ahto Jalak
 * @since 22.03.2023
 */
@Service
public class StatsService implements IStatsService {

    Logger logger = LoggerFactory.getLogger(StatsService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserBLLMapper userBLLMapper;

    @Autowired
    private ProductBLLMapper productBLLMapper;

    public StatsBLLDTO getPublicStats() {
        StatsBLLDTO stats = new StatsBLLDTO();
        stats.setNumOfProducts(1);
        stats.setNumOfPriceUpdates(2);
        stats.setNumOfUsers(3);
        return stats;
    }
}
