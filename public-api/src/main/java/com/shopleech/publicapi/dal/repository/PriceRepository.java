package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Repository
public interface PriceRepository extends JpaRepository<Price, Integer>, PriceRepositoryCustom {
    @Query(value = "SELECT COUNT(*) FROM price", nativeQuery = true)
    int getPriceUpdateCount();

    @Query(value = "SELECT MIN(pr.amount) FROM price pr " +
            "JOIN offer o ON o.id = pr.offer_id " +
            "JOIN product p ON p.id = o.product_id " +
            "WHERE p.barcode = :barcode", nativeQuery = true)
    Double getMinPriceByBarcode(String barcode);

    @Query(value = "SELECT * FROM price where offer_id = :offerId " +
            "ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Price getLastPriceByOfferId(Integer offerId);
}
