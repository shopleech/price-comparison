package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.dal.repository.CategoryRepository;
import com.shopleech.publicapi.domain.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Service
public class CategoryService {
    Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    protected CategoryRepository categoryRepository;

//    @Autowired
//    protected CategoryBLLMapper categoryMapper;

    public void createCategory(Category data) {
        categoryRepository.save(data);
    }

    public Category get(int id) {
        return categoryRepository.getCategoryById(id);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
