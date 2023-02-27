package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ahto Jalak
 * @since 25.02.2023
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>, RoleRepositoryCustom {
}
