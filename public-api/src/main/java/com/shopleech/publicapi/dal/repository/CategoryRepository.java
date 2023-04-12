package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>, CategoryRepositoryCustom {
    @Query(value = "SELECT * FROM category where parent_category_id = :id", nativeQuery = true)
    List<Category> getAllByCategoryId(Integer id);

    @Query(value = "SELECT * FROM category WHERE parent_category_id ISNULL" +
            " OR parent_category_id = 0", nativeQuery = true)
    List<Category> getMainCategoryList();

    @Query(value = "SELECT * FROM category where name ilike '%'||:name||'%'", nativeQuery = true)
    List<Category> getAllByName(String name);
}
