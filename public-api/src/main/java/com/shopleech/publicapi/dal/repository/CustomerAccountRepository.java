package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.domain.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 28.03.2023
 */
@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Integer>, CustomerAccountRepositoryCustom {
    @Query(value = "SELECT * FROM customer_account where account_id = :id", nativeQuery = true)
    List<CustomerAccount> findAllByAccountId(Integer id);
}
