package com.shopleech.publicapi.bll.mapper;

import com.shopleech.publicapi.bll.dto.CategoryBLLDTO;
import com.shopleech.publicapi.dal.dto.CategoryDALDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 07.02.2023
 */
public class CategoryBLLMapper {

   public List<CategoryBLLDTO> mapToDto(List<CategoryDALDTO> categories) {
      return categories.stream()
              .map(this::mapToDto).collect(Collectors.toList());
   }

   public CategoryBLLDTO mapToDto(CategoryDALDTO c) {
      return new CategoryBLLDTO(
              c.getId(),
              c.getName()
      );
   }

   public CategoryDALDTO mapToEntity(CategoryBLLDTO newCategory) {
      CategoryDALDTO entity = new CategoryDALDTO();
      entity.setId(newCategory.getId());
      entity.setName(newCategory.getName());
      return entity;
   }
}
