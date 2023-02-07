package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.dto.CategoryBLLDTO;
import com.shopleech.publicapi.bll.mapper.CategoryBLLMapper;
import com.shopleech.publicapi.dal.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Service
@RequiredArgsConstructor
public class CategoryService {

   protected CategoryRepository categoryRepository;

   protected CategoryBLLMapper categoryMapper;

   public void createCategory(CategoryBLLDTO data) {
      categoryRepository.addCategory(categoryMapper.mapToEntity(data));
   }

   public CategoryBLLDTO get(int id) {
      return categoryMapper.mapToDto(categoryRepository.getCategoryById(id));
   }

   public List<CategoryBLLDTO> getAll() {
      return categoryMapper.mapToDto(categoryRepository.getAllCategories());
   }
}
