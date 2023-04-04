package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.AccountDALDTO;
import com.shopleech.publicapi.dal.mapper.AccountDALMapper;
import com.shopleech.publicapi.domain.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Component
public class AccountRepositoryImpl implements AccountRepositoryCustom {

    @Autowired
    private AccountDALMapper accountDALMapper;

    @PersistenceContext
    EntityManager em;

    @Override
    public AccountDALDTO addAccount(AccountDALDTO account) {
        return null;
    }

    @Override
    public AccountDALDTO getAccountById(Integer id) {
        return null;
    }

    @Override
    public List<AccountDALDTO> getAllAccounts() {
        TypedQuery<Account> query = em.createQuery("select p from Account p", Account.class);
        List<Account> accounts = query.getResultList();

        return accountDALMapper.mapToDto(accounts);
    }

    @Override
    public Account createAccount(AccountDALDTO account) {
        return null;
    }
}
