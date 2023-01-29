package com.shopleech.publicapi.dal.mapper;

import com.shopleech.publicapi.dal.dto.Account;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class AccountDALMapper {

   public List<Account> mapToDto(List<com.shopleech.publicapi.domain.Account> accounts) {
      return accounts.stream()
              .map(this::mapToDto).collect(Collectors.toList());
   }

   public Account mapToDto(com.shopleech.publicapi.domain.Account c) {
      return new Account(
              c.getId(),
              c.getName(),
              c.getDescription()
      );
   }

   public com.shopleech.publicapi.domain.Account mapToEntity(Account newAccount) {
      com.shopleech.publicapi.domain.Account entity = new com.shopleech.publicapi.domain.Account();
      entity.setId(newAccount.getId());
      entity.setName(newAccount.getName());
      entity.setDescription(newAccount.getDescription());
      return entity;
   }

}
