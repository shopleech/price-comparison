package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.domain.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Integer>, AlarmRepositoryCustom {
}
