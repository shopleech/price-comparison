package com.shopleech.publicapi.dal.mapper;

import com.shopleech.publicapi.dal.dto.CategoryDALDTO;
import com.shopleech.publicapi.domain.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class CategoryDALMapper {

//    @Autowired
//    ProductDALMapper productDALMapper;

    public List<CategoryDALDTO> mapToDto(List<Category> categories) {
        return categories.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public CategoryDALDTO mapToDto(Category c) {

        CategoryDALDTO dto = new CategoryDALDTO();
        dto.setId(c.getId());
        // dto.setParentCategory(mapToDto(c.getParentCategory()));
        dto.setName(c.getName());
        dto.setCategoryTypeCode(c.getCategoryTypeCode());
        // productDALMapper.mapToDto(c.getProducts())
        // mapToDto(c.getCategories())

        return dto;
    }

    public List<Category> mapToEntity(List<CategoryDALDTO> categories) {
        return categories.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Category mapToEntity(CategoryDALDTO newCategory) {

        Category entity = new Category();
        entity.setId(newCategory.getId());
//        entity.setParentCategory(mapToEntity(newCategory.getParentCategory()));
        entity.setName(newCategory.getName());
//        entity.setProducts(productDALMapper.mapToEntity(newCategory.getProducts()));
//        entity.setCategories(mapToEntity(newCategory.getCategories()));

        return entity;
    }

}
