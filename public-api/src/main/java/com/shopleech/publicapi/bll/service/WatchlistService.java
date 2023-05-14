package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.service.model.IWatchlistService;
import com.shopleech.publicapi.dal.repository.CustomerRepository;
import com.shopleech.publicapi.dal.repository.ProductRepository;
import com.shopleech.publicapi.dal.repository.UserRepository;
import com.shopleech.publicapi.dal.repository.WatchlistRepository;
import com.shopleech.publicapi.domain.Watchlist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Service
public class WatchlistService implements IWatchlistService {
    Logger logger = LoggerFactory.getLogger(WatchlistService.class);

    @Autowired
    protected WatchlistRepository watchlistRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected ProductRepository productRepository;
    @Autowired
    protected UserService userService;

    @Override
    public Watchlist add(Watchlist data) throws Exception {
        var check = watchlistRepository.findByProduct(
                data.getCustomer().getId(), data.getProduct().getId());

        if (check != null) {
            throw new Exception("watchlist item exists");
        }

        return watchlistRepository.save(data);
    }

    @Override
    public Watchlist get(Integer id) throws Exception {
        var item = watchlistRepository.findById(id);

        if (item.isEmpty()) {
            throw new Exception("watchlist not found");
        }

        return item.get();
    }

    @Override
    public List<Watchlist> getAll() {
        var customerId = userService.getCurrentUser().getCustomer().getId();

        return watchlistRepository.findAllByCustomerId(customerId);
    }

    @Override
    public Integer remove(Integer id) {
        watchlistRepository.deleteById(id);

        try {
            var item = watchlistRepository.findById(id);
            if (item.isPresent()) {
                throw new Exception("watchlist removal failed");
            }
        } catch (Exception e) {
            logger.info("watchlist not found");
        }

        return id;
    }

    @Override
    public Watchlist update(Integer id, Watchlist newItem) throws Exception {
        var item = get(id);

        return watchlistRepository.save(item);
    }
}
