package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ahto Jalak
 * @since 04.02.2023
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {
}