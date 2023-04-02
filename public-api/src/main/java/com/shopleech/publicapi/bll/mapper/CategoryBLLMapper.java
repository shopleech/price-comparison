package com.shopleech.publicapi.bll.mapper;

import com.shopleech.publicapi.bll.dto.CategoryBLLDTO;
import com.shopleech.publicapi.dal.dto.CategoryDALDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 07.02.2023
 */
@Component
public class CategoryBLLMapper {

//    @Autowired
//    ProductBLLMapper productBLLMapper;

    public List<CategoryBLLDTO> mapToDto(List<CategoryDALDTO> categories) {
        return categories.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public CategoryBLLDTO mapToDto(CategoryDALDTO c) {

        CategoryBLLDTO dto = new CategoryBLLDTO();
        dto.setId(c.getId());
        // mapToDto(c.getParentCategory()),
        dto.setName(c.getName());
        dto.setCategoryTypeCode(c.getCategoryTypeCode());
        // productBLLMapper.mapToDto(c.getProducts()),
        // mapToDto(c.getCategories())

        return dto;
    }

    public List<CategoryDALDTO> mapToEntity(List<CategoryBLLDTO> categories) {
        return categories.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public CategoryDALDTO mapToEntity(CategoryBLLDTO newCategory) {

        CategoryDALDTO entity = new CategoryDALDTO();
        entity.setId(newCategory.getId());
        // entity.setParentCategory(mapToEntity(newCategory.getParentCategory()));
        entity.setName(newCategory.getName());
        // entity.setProducts(productBLLMapper.mapToEntity(newCategory.getProducts()));
        // entity.setCategories(mapToEntity(newCategory.getCategories()));

        return entity;
    }
}
