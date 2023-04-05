package com.shopleech.publicapi.bll.service.model;

import com.shopleech.publicapi.domain.Account;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 05.04.2023
 */
public interface IAccountService {
    Account get(int id) throws Exception;

    List<Account> getAll();

    Integer remove(Integer id) throws Exception;

    Account update(Integer id, Account account) throws Exception;

    Account add(Account account);
}
