package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 04.02.2023
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, ProductRepositoryCustom {
    @Query(value = "SELECT * FROM product where id = :id", nativeQuery = true)
    Product getProductById(Integer id);

    @Query(value = "SELECT * FROM product where name like '%'||:keyword||'%' " +
            "or barcode like '%'||:keyword||'%'", nativeQuery = true)
    List<Product> getAllProductsByKeyword(String keyword);

    @Query(value = "SELECT COUNT(*) FROM product", nativeQuery = true)
    int getProductCount();
}
