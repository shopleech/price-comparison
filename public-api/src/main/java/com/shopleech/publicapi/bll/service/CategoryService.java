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

    public Category add(Category data) {
        logger.info("add category");
        return categoryRepository.save(data);
    }

    public Category get(int id) throws Exception {
        logger.info("get category");
        var item = categoryRepository.findById(id);

        if (item.isEmpty()) {
            throw new Exception("category not found");
        }

        return item.get();
    }

    public List<Category> getAll() {
        logger.info("get all categories");
        return categoryRepository.findAll();
    }
}
