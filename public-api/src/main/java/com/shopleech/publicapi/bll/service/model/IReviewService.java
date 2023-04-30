package com.shopleech.publicapi.bll.service.model;

import com.shopleech.publicapi.domain.Review;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 05.04.2023
 */
public interface IReviewService {
    List<Review> getAll();

    Review get(Integer id) throws Exception;

    Review add(Review review) throws Exception;

    Review update(Integer id, Review mapToEntity) throws Exception;

    Integer remove(Integer id) throws Exception;
}
