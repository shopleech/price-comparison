package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.bll.dto.AccountBLLDTO;
import com.shopleech.publicapi.dto.v1.AccountDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class AccountMapper {

   public List<AccountDTO> mapToDto(List<AccountBLLDTO> accounts) {
      return accounts.stream()
              .map(this::mapToDto).collect(Collectors.toList());
   }

   public AccountDTO mapToDto(AccountBLLDTO c) {
      return new AccountDTO(
              c.getId(),
              c.getStatus()
      );
   }

   public AccountBLLDTO mapToEntity(AccountDTO newAccount) {
      AccountBLLDTO entity = new AccountBLLDTO();
      entity.setId(newAccount.getId());
      entity.setStatus(newAccount.getStatus());
      return entity;
   }

}
