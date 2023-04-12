package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 30.03.2023
 */
@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer>, ShopRepositoryCustom {
    @Query(value = "SELECT COUNT(*) FROM shop", nativeQuery = true)
    int countAll();

    @Query(value = "SELECT * FROM shop where name ilike '%'||:name||'%'", nativeQuery = true)
    List<Shop> findByName(String name);
}
