package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.service.model.ICategoryService;
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
public class CategoryService implements ICategoryService {
    Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    protected CategoryRepository categoryRepository;

    @Override
    public Category add(Category data) {
        logger.info("add category");
        return categoryRepository.save(data);
    }

    @Override
    public Category get(int id) throws Exception {
        logger.info("get category");
        var item = categoryRepository.findById(id);

        if (item.isEmpty()) {
            throw new Exception("category not found");
        }

        return item.get();
    }

    @Override
    public List<Category> getAll() {
        logger.info("get all categories");
        return categoryRepository.findAll();
    }

    @Override
    public Category update(Integer id, Category category) {
        return category;
    }

    @Override
    public Integer remove(Integer id) {
        return id;
    }
}
