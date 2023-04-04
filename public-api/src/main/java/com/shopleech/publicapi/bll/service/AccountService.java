package com.shopleech.publicapi.bll.service;

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
public class AccountService {
    Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    protected AccountRepository accountRepository;

    public Account createAccount(Account data) {
        return accountRepository.save(data);
    }

    public Account get(int id) throws Exception {
        var item = accountRepository.findById(id);

        if (item.isEmpty()) {
            throw new Exception("account not found");
        }

        return item.get();
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }
}
