package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>, ReviewRepositoryCustom {
    @Query(value = "SELECT * FROM review where product_id = :productId " +
            "AND customer_id = :customerId", nativeQuery = true)
    Review findByProduct(Integer customerId, Integer productId);

    @Query(value = "SELECT * FROM review where customer_id = :customerId", nativeQuery = true)
    List<Review> findAllByCustomerId(Integer customerId);
}
