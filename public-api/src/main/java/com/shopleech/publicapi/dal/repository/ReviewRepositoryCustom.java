package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.ReviewDALDTO;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
public interface ReviewRepositoryCustom {

   void addReview(ReviewDALDTO mapToEntity);

   ReviewDALDTO getReviewById(Integer id);

   List<ReviewDALDTO> getAllReviews();
}
