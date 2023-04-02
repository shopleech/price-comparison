package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Ahto Jalak
 * @since 04.02.2023
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {
    @Query(value = "SELECT * FROM _user where email = ?1", nativeQuery = true)
    User getUserByUsername(String username);

    @Query(value = "SELECT COUNT(*) FROM _user where email = ?1", nativeQuery = true)
    int isUsernameExist(String username);
}