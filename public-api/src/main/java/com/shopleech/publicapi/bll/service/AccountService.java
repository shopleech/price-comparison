package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.dto.Account;
import com.shopleech.publicapi.bll.mapper.AccountBLLMapper;
import com.shopleech.publicapi.dal.repository.CustomAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Service
public class AccountService {

   @Autowired
   protected CustomAccountRepository accountRepository;

   @Autowired
   protected AccountBLLMapper accountMapper;

   public AccountService() {
   }

   public void createAccount(Account data) {
      accountRepository.add(accountMapper.mapToEntity(data));
   }

   public Account get(int id) {
      return accountMapper.mapToDto(accountRepository.getById(id));
   }

   public Account getByName(String name) {
      return accountMapper.mapToDto(accountRepository.getByName(name));
   }

   public List<Account> getAll() {
      return accountMapper.mapToDto(accountRepository.getAll());
   }
}
