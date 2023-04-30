package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.domain.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 29.03.2023
 */
@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer>, OfferRepositoryCustom {
    @Query(value = "SELECT * FROM offer where product_id = :id", nativeQuery = true)
    List<Offer> getAllByProductId(Integer id);

    @Query(value = "SELECT * FROM offer where product_id = :productId AND account_id = :accountId " +
            "AND shop_id = :shopId", nativeQuery = true)
    Offer findByProduct(Integer accountId, Integer productId, Integer shopId);
}
