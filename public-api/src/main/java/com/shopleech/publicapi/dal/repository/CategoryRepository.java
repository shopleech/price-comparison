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
    @Query(value = "SELECT COUNT(*) FROM category where id = ?1", nativeQuery = true)
    Category getCategoryById(int id);
}
