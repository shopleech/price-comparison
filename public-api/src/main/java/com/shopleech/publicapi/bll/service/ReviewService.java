package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.service.model.IReviewService;
import com.shopleech.publicapi.dal.repository.ReviewRepository;
import com.shopleech.publicapi.domain.Review;
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
public class ReviewService implements IReviewService {
    Logger logger = LoggerFactory.getLogger(ReviewService.class);

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserService userService;

    @Override
    public List<Review> getAll() {
        var customerId = userService.getCurrentUser().getCustomer().getId();

        return reviewRepository.findAllByCustomerId(customerId);
    }

    @Override
    public Review get(Integer id) throws Exception {
        var item = reviewRepository.findById(id);

        if (item.isEmpty()) {
            throw new Exception("review not found");
        }

        return item.get();
    }

    @Override
    public Review add(Review review) throws Exception {
        var check = reviewRepository.findByProduct(
                review.getCustomer().getId(), review.getProduct().getId());

        if (check != null) {
            throw new Exception("review item exists");
        }

        return reviewRepository.save(review);
    }

    @Override
    public Review update(Integer id, Review mapToEntity) throws Exception {
        var item = get(id);
        return reviewRepository.save(item);
    }

    @Override
    public Integer remove(Integer id) throws Exception {
        reviewRepository.deleteById(id);

        var item = reviewRepository.findById(id);
        if (item.isPresent()) {
            throw new Exception("review removal failed");
        }

        return id;
    }
}
