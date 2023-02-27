package com.shopleech.publicapi.dal.mapper;

import com.shopleech.publicapi.dal.dto.ReviewDALDTO;
import com.shopleech.publicapi.domain.Review;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class ReviewDALMapper {

    public List<ReviewDALDTO> mapToDto(List<Review> reviews) {
        return reviews.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public ReviewDALDTO mapToDto(Review c) {
        return new ReviewDALDTO(
                c.getId(),
                c.getReviewTypeCode(),
                c.getScore(),
                c.getDescription()
        );
    }

    public Review mapToEntity(ReviewDALDTO newReview) {
        Review entity = new Review();
        entity.setId(newReview.getId());
        entity.setReviewTypeCode(newReview.getReviewTypeCode());
        entity.setScore(newReview.getScore());
        entity.setDescription(newReview.getDescription());
        return entity;
    }

}
