package com.shopleech.publicapi.dal.model.repository;

import com.shopleech.base.dal.model.IRepository;
import com.shopleech.publicapi.dal.dto.Account;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
public interface IAccountRepository extends IRepository<Account> {
   List<Account> getBestAccounts();
}
