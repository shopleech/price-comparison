package com.shopleech.publicapi.bll.mapper;

import com.shopleech.publicapi.bll.dto.ReviewBLLDTO;
import com.shopleech.publicapi.dal.dto.ReviewDALDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 23.03.2023
 */
@Component
public class ReviewBLLMapper {

//    @Autowired
//    CustomerBLLMapper customerBLLMapper;
//    @Autowired
//    ProductBLLMapper productBLLMapper;

    public List<ReviewBLLDTO> mapToDto(List<ReviewDALDTO> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public ReviewBLLDTO mapToDto(ReviewDALDTO c) {
        ReviewBLLDTO dto = new ReviewBLLDTO();
        dto.setId(c.getId());
        // customerBLLMapper.mapToDto(c.getCustomer()),
        // productBLLMapper.mapToDto(c.getProduct()),
        dto.setReviewTypeCode(c.getReviewTypeCode());
        dto.setScore(c.getScore());
        dto.setDescription(c.getDescription());

        return dto;
    }

    public List<ReviewDALDTO> mapToEntity(List<ReviewBLLDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public ReviewDALDTO mapToEntity(ReviewBLLDTO entity) {

        ReviewDALDTO c = new ReviewDALDTO();
        c.setId(entity.getId());
        // c.setCustomer(customerBLLMapper.mapToEntity(entity.getCustomer()));
        // c.setProduct(productBLLMapper.mapToEntity(entity.getProduct()));
        c.setReviewTypeCode(entity.getReviewTypeCode());
        c.setScore(entity.getScore());
        c.setDescription(entity.getDescription());

        return c;
    }
}
