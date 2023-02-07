package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.AccountDALDTO;
import com.shopleech.publicapi.dal.mapper.AccountDALMapper;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
public class AccountRepositoryImpl implements AccountRepositoryCustom {

   private AccountRepository accountRepository;

   private AccountDALMapper accountDALMapper;

   @Override
   public void addAccount(AccountDALDTO account) {
      // accountRepository.add(accountDALMapper.mapToEntity(account));
   }

   @Override
   public AccountDALDTO getAccountById(Integer id) {
      return accountDALMapper.mapToDto(accountRepository.getReferenceById(id));
   }

   @Override
   public List<AccountDALDTO> getAllAccounts() {
      return accountDALMapper.mapToDto(accountRepository.findAll());
   }
}
