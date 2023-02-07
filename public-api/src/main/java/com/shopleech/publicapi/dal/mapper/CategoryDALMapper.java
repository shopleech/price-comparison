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

   public List<CategoryDALDTO> mapToDto(List<Category> categories) {
      return categories.stream()
              .map(this::mapToDto).collect(Collectors.toList());
   }

   public CategoryDALDTO mapToDto(Category c) {
      return new CategoryDALDTO(
              c.getId(),
              c.getName()
      );
   }

   public Category mapToEntity(CategoryDALDTO newCategory) {
      Category entity = new Category();
      entity.setId(newCategory.getId());
      entity.setName(newCategory.getName());
      return entity;
   }

}
