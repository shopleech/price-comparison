package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.domain.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ahto Jalak
 * @since 29.03.2023
 */
@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer>, OfferRepositoryCustom {
}
