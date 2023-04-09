package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.bll.service.CategoryService;
import com.shopleech.publicapi.domain.Category;
import com.shopleech.publicapi.dto.v1.CategoryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    CategoryService categoryService;

    public List<CategoryDTO> mapToDto(List<Category> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public CategoryDTO mapToDto(Category c) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(c.getId());
        if (c.getParentCategory() != null) {
            dto.setParentCategoryId(c.getParentCategory().getId());
        }
        dto.setCategoryTypeCode(c.getCategoryTypeCode());
        dto.setName(c.getName());

        return dto;
    }

    public List<Category> mapToEntity(List<CategoryDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Category mapToEntity(CategoryDTO entity) {
        Category c = new Category();
        c.setId(entity.getId());
        try {
            c.setParentCategory(categoryService.get(entity.getParentCategoryId()));
        } catch (Exception e) {
            logger.error("category mapper failed");
        }
        c.setCategoryTypeCode(entity.getCategoryTypeCode());
        c.setName(entity.getName());

        return c;
    }
}
