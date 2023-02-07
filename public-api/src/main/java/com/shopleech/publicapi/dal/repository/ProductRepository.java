package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ahto Jalak
 * @since 04.02.2023
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, ProductRepositoryCustom {

//   void add(Product product);
}
