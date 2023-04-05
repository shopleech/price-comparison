package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.domain.Account;
import com.shopleech.publicapi.dto.v1.AccountDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class AccountMapper {
    Logger logger = LoggerFactory.getLogger(AccountMapper.class);

    public List<AccountDTO> mapToDto(List<Account> accounts) {
        return accounts.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public AccountDTO mapToDto(Account c) {
        return new AccountDTO(
                c.getId(),
                c.getStatus()
        );
    }

    public Account mapToEntity(AccountDTO newAccount) {
        Account entity = new Account();
        entity.setId(newAccount.getId());
        entity.setStatus(newAccount.getStatus());
        return entity;
    }

}
