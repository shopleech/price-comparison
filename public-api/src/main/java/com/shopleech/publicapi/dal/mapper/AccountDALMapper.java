package com.shopleech.publicapi.dal.mapper;

import com.shopleech.publicapi.dal.dto.AccountDALDTO;
import com.shopleech.publicapi.domain.Account;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class AccountDALMapper {

   public List<AccountDALDTO> mapToDto(List<Account> accounts) {
      return accounts.stream()
              .map(this::mapToDto).collect(Collectors.toList());
   }

   public AccountDALDTO mapToDto(Account c) {
      return new AccountDALDTO(
              c.getId(),
              c.getStatus()
      );
   }

   public Account mapToEntity(AccountDALDTO newAccount) {
      Account entity = new Account();
      entity.setId(newAccount.getId());
      entity.setStatus(newAccount.getStatus());
      return entity;
   }

}
