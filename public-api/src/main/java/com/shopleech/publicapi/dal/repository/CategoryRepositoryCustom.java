package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.CategoryDALDTO;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
public interface CategoryRepositoryCustom {

    void addCategory(CategoryDALDTO mapToEntity);

    CategoryDALDTO getCategoryById(Integer id);

    List<CategoryDALDTO> getAllCategories();
}
