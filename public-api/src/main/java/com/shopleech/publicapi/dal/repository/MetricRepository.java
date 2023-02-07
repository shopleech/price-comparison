package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.domain.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Repository
public interface MetricRepository extends JpaRepository<Metric, Integer>, MetricRepositoryCustom {

//   void add(Metric metric);
}
