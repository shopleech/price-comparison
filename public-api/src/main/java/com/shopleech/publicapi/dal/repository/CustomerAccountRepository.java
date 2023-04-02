package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.domain.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ahto Jalak
 * @since 28.03.2023
 */
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Integer>, CustomerAccountRepositoryCustom {
}
