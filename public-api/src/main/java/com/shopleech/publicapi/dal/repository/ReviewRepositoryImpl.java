package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.ReviewDALDTO;
import com.shopleech.publicapi.dal.mapper.ReviewDALMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Component
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private ReviewRepository reviewRepository;

    private ReviewDALMapper reviewDALMapper;

    @Override
    public void addReview(ReviewDALDTO review) {
        //reviewRepository.add(reviewDALMapper.mapToEntity(review));
    }

    @Override
    public ReviewDALDTO getReviewById(Integer id) {
        return reviewDALMapper.mapToDto(reviewRepository.getReferenceById(id));
    }

    @Override
    public List<ReviewDALDTO> getAllReviews() {
        return reviewDALMapper.mapToDto(reviewRepository.findAll());
    }
}
