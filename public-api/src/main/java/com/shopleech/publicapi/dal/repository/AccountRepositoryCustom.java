package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.AccountDALDTO;
import com.shopleech.publicapi.domain.Account;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
public interface AccountRepositoryCustom {

    AccountDALDTO addAccount(AccountDALDTO mapToEntity);

    AccountDALDTO getAccountById(Integer id);

    List<AccountDALDTO> getAllAccounts();

    Account createAccount(AccountDALDTO account);
}
