package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.domain.Category;
import com.shopleech.publicapi.dto.v1.CategoryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 03.04.2023
 */
@Component
public class CategoryMapper {
    Logger logger = LoggerFactory.getLogger(CategoryMapper.class);
    
    public List<CategoryDTO> mapToDto(List<Category> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public CategoryDTO mapToDto(Category c) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(c.getId());
        // dto.setParentCategory();
        dto.setName(c.getName());
        // dto.setCategoryTypeCode();

        return dto;
    }

    public List<Category> mapToEntity(List<CategoryDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Category mapToEntity(CategoryDTO entity) {

        Category c = new Category();
        c.setId(entity.getId());
        // c.setParentCategory();
        c.setName(entity.getName());
        // c.setCategoryTypeCode();

        return c;
    }
}
