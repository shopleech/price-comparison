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

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
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
    public Review add(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review update(Integer id, Review mapToEntity) throws Exception {
        var item = get(id);
        return reviewRepository.save(item);
    }

    @Override
    public Integer remove(Integer id) {
        return id;
    }
}
