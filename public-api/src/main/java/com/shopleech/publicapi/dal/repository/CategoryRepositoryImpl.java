package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.CategoryDALDTO;
import com.shopleech.publicapi.dal.mapper.CategoryDALMapper;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    private CategoryRepository categoryRepository;

    private CategoryDALMapper categoryDALMapper;

    @Override
    public void addCategory(CategoryDALDTO category) {
        // categoryRepository.add(categoryDALMapper.mapToEntity(category));
    }

    @Override
    public CategoryDALDTO getCategoryById(Integer id) {
        return categoryDALMapper.mapToDto(categoryRepository.getReferenceById(id));
    }

    @Override
    public List<CategoryDALDTO> getAllCategories() {
        return categoryDALMapper.mapToDto(categoryRepository.findAll());
    }
}
