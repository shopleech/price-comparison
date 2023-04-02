package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.CategoryDALDTO;
import com.shopleech.publicapi.dal.mapper.CategoryDALMapper;
import com.shopleech.publicapi.domain.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    @Autowired
    private CategoryDALMapper categoryDALMapper;

    @PersistenceContext
    EntityManager em;

    @Override
    public void addCategory(CategoryDALDTO category) {

        Session session = em.unwrap(Session.class);

        Category category1 = categoryDALMapper.mapToEntity(category);
        session.persist(category1);

    }

    @Override
    public CategoryDALDTO getCategoryById(Integer id) {

        Category category = em.find(Category.class, id);

        return categoryDALMapper.mapToDto(category);
    }

    @Override
    public List<CategoryDALDTO> getAllCategories() {

        TypedQuery<Category> query = em.createQuery("select p from Category p", Category.class);
        List<Category> categories = query.getResultList();

        return categoryDALMapper.mapToDto(categories);
    }
}
