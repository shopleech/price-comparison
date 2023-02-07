package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>, AccountRepositoryCustom {

//   void add(Account account);
}
