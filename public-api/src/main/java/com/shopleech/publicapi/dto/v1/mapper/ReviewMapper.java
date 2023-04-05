package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.domain.Review;
import com.shopleech.publicapi.dto.v1.ReviewDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 05.04.2023
 */
@Component
public class ReviewMapper {
    Logger logger = LoggerFactory.getLogger(ReviewMapper.class);

    public List<ReviewDTO> mapToDto(List<Review> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public ReviewDTO mapToDto(Review c) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(c.getId());
        // dto.setParentReview();
        //dto.setName(c.getName());
        // dto.setReviewTypeCode();

        return dto;
    }

    public List<Review> mapToEntity(List<ReviewDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Review mapToEntity(ReviewDTO entity) {

        Review c = new Review();
        c.setId(entity.getId());
        // c.setParentReview();
        //c.setName(entity.getName());
        // c.setReviewTypeCode();

        return c;
    }
}
