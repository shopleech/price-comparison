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

//    @Autowired
//    CustomerAccountDALMapper customerAccountDALMapper;
//    @Autowired
//    OfferDALMapper offerDALMapper;

    public List<AccountDALDTO> mapToDto(List<Account> accounts) {
        return accounts.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public AccountDALDTO mapToDto(Account c) {

        AccountDALDTO dto = new AccountDALDTO();
        dto.setId(c.getId());
        dto.setStatus(c.getStatus());
//        dto.setCustomerAccounts(customerAccountDALMapper.mapToDto(c.getCustomerAccounts()));
//        dto.setOffers(offerDALMapper.mapToDto(c.getOffers()));

        return dto;
    }

    public List<Account> mapToEntity(List<AccountDALDTO> accounts) {
        return accounts.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Account mapToEntity(AccountDALDTO newAccount) {

        Account entity = new Account();
        entity.setId(newAccount.getId());
        entity.setStatus(newAccount.getStatus());
//        entity.setCustomerAccounts(customerAccountDALMapper.mapToEntity(newAccount.getCustomerAccounts()));
//        entity.setOffers(offerDALMapper.mapToEntity(newAccount.getOffers()));

        return entity;
    }

}
