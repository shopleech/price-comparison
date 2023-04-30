package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.domain.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, Integer>, WatchlistRepositoryCustom {
    @Query(value = "SELECT * FROM watchlist where customer_id = :customerId", nativeQuery = true)
    List<Watchlist> findAllByCustomerId(Integer customerId);

    @Query(value = "SELECT * FROM watchlist where product_id = :productId " +
            "AND customer_id = :customerId", nativeQuery = true)
    Watchlist findByProduct(Integer customerId, Integer productId);
}
