package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.domain.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Integer>, AlarmRepositoryCustom {
    @Query(value = "SELECT * FROM alarm where product_id = :productId " +
            "AND customer_id = :customerId", nativeQuery = true)
    Alarm findByProduct(Integer customerId, Integer productId);

    @Query(value = "SELECT * FROM alarm where customer_id = :customerId", nativeQuery = true)
    List<Alarm> findAllByCustomerId(Integer customerId);
}
