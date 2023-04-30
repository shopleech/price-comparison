package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.bll.service.ProductService;
import com.shopleech.publicapi.bll.service.UserService;
import com.shopleech.publicapi.domain.Review;
import com.shopleech.publicapi.dto.v1.ReviewDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    private ProductMapper productMapper;

    public List<ReviewDTO> mapToDto(List<Review> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public ReviewDTO mapToDto(Review c) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(c.getId());
        try {
            dto.setProductId(c.getProduct().getId());
            dto.setProduct(productMapper.mapToDto(
                    productService.get(c.getProduct().getId())));
        } catch (Exception e) {
            logger.error("review mapper failed");
        }
        dto.setReviewTypeCode(c.getReviewTypeCode());
        dto.setScore(c.getScore());
        dto.setDescription(c.getDescription());

        return dto;
    }

    public List<Review> mapToEntity(List<ReviewDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Review mapToEntity(ReviewDTO entity) {
        Review c = new Review();
        c.setId(entity.getId());
        c.setCustomer(userService.getCurrentUser().getCustomer());
        try {
            c.setProduct(productService.get(entity.getProductId()));
        } catch (Exception e) {
            logger.error("review mapper failed");
        }
        c.setReviewTypeCode(entity.getReviewTypeCode());
        c.setScore(entity.getScore());
        c.setDescription(entity.getDescription());

        return c;
    }
}
