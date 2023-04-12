package com.shopleech.publicapi.bll.service.model;

import com.shopleech.publicapi.domain.Category;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 05.04.2023
 */
public interface ICategoryService {
    Category add(Category data);

    Category get(int id) throws Exception;

    List<Category> getAll();

    Category update(Integer id, Category category);

    Integer remove(Integer id);

    List<Category> getAllByCategoryId(Integer id);

    List<Category> getAllByCategory(Category entity);
}
