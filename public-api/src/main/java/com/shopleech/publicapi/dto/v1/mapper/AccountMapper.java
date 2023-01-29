package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.dto.v1.Account;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class AccountMapper {

   public List<Account> mapToDto(List<com.shopleech.publicapi.bll.dto.Account> accounts) {
      return accounts.stream()
              .map(this::mapToDto).collect(Collectors.toList());
   }

   public Account mapToDto(com.shopleech.publicapi.bll.dto.Account c) {
      return new Account(
              c.getId(),
              c.getName(),
              c.getDescription()
      );
   }

   public com.shopleech.publicapi.bll.dto.Account mapToEntity(Account newAccount) {
      com.shopleech.publicapi.bll.dto.Account entity = new com.shopleech.publicapi.bll.dto.Account();
      entity.setId(newAccount.getId());
      entity.setName(newAccount.getName());
      entity.setDescription(newAccount.getDescription());
      return entity;
   }

}
