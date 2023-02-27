package com.shopleech.publicapi.bll.mapper;

import com.shopleech.publicapi.bll.dto.AccountBLLDTO;
import com.shopleech.publicapi.dal.dto.AccountDALDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class AccountBLLMapper {

    public List<AccountBLLDTO> mapToDto(List<AccountDALDTO> accounts) {
        return accounts.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public AccountBLLDTO mapToDto(AccountDALDTO c) {
        return new AccountBLLDTO(
                c.getId(),
                c.getStatus()
        );
    }

    public AccountDALDTO mapToEntity(AccountBLLDTO newAccount) {
        AccountDALDTO entity = new AccountDALDTO();
        entity.setId(newAccount.getId());
        entity.setStatus(newAccount.getStatus());
        return entity;
    }

}
