package com.shopleech.publicapi.bll.mapper;

import com.shopleech.publicapi.bll.dto.Account;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class AccountBLLMapper {

   public List<Account> mapToDto(List<com.shopleech.publicapi.dal.dto.Account> accounts) {
      return accounts.stream()
              .map(this::mapToDto).collect(Collectors.toList());
   }

   public Account mapToDto(com.shopleech.publicapi.dal.dto.Account c) {
      return new Account(
              c.getId(),
              c.getStatus()
      );
   }

   public com.shopleech.publicapi.dal.dto.Account mapToEntity(Account newAccount) {
      com.shopleech.publicapi.dal.dto.Account entity = new com.shopleech.publicapi.dal.dto.Account();
      entity.setId(newAccount.getId());
      entity.setStatus(newAccount.getStatus());
      return entity;
   }

}
