package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.service.model.IAccountService;
import com.shopleech.publicapi.dal.repository.AccountRepository;
import com.shopleech.publicapi.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Service
public class AccountService implements IAccountService {
    Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    protected AccountRepository accountRepository;

    @Override
    public Account get(int id) throws Exception {
        var item = accountRepository.findById(id);

        if (item.isEmpty()) {
            throw new Exception("account not found");
        }

        return item.get();
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Integer remove(Integer id) throws Exception {
        accountRepository.delete(get(id));
        return id;
    }

    @Override
    public Account update(Integer id, Account account) throws Exception {
        var item = get(id);

        return get(id);
    }

    @Override
    public Account add(Account account) {
        return account;
    }
}
