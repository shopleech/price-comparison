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

//    @Autowired
//    CustomerDALMapper customerDALMapper;
//    @Autowired
//    ProductDALMapper productDALMapper;

    public List<ReviewDALDTO> mapToDto(List<Review> reviews) {
        return reviews.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public ReviewDALDTO mapToDto(Review c) {

        ReviewDALDTO dto = new ReviewDALDTO();
        dto.setId(c.getId());
        // customerDALMapper.mapToDto(c.getCustomer()),
        // productDALMapper.mapToDto(c.getProduct()),
        dto.setReviewTypeCode(c.getReviewTypeCode());
        dto.setScore(c.getScore());
        dto.setDescription(c.getDescription());

        return dto;
    }

    public List<Review> mapToEntity(List<ReviewDALDTO> reviews) {
        return reviews.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Review mapToEntity(ReviewDALDTO entity) {

        Review c = new Review();
        c.setId(entity.getId());
//        c.setCustomer(customerDALMapper.mapToEntity(entity.getCustomer()));
//        c.setProduct(productDALMapper.mapToEntity(entity.getProduct()));
        c.setReviewTypeCode(entity.getReviewTypeCode());
        c.setScore(entity.getScore());
        c.setDescription(entity.getDescription());

        return c;
    }
}
