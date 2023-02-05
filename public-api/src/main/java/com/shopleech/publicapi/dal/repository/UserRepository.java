package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Ahto Jalak
 * @since 04.02.2023
 */
public interface UserRepository extends JpaRepository<User, Integer> {

   Optional<User> findByEmail(String email);

}