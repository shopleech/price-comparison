package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Ahto Jalak
 * @since 04.02.2023
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
