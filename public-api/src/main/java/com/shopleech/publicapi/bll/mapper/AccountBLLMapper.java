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

//    @Autowired
//    private CustomerAccountBLLMapper customerAccountBLLMapper;
//    @Autowired
//    private OfferBLLMapper offerBLLMapper;

    public List<AccountBLLDTO> mapToDto(List<AccountDALDTO> accounts) {
        return accounts.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public AccountBLLDTO mapToDto(AccountDALDTO c) {

        AccountBLLDTO dto = new AccountBLLDTO();
        dto.setId(c.getId());
        dto.setStatus(c.getStatus());
//        dto.setCustomerAccounts(customerAccountBLLMapper.mapToDto(c.getCustomerAccounts()));
//        dto.setOffers(offerBLLMapper.mapToDto(c.getOffers()));

        return dto;
    }

    public List<AccountDALDTO> mapToEntity(List<AccountBLLDTO> accounts) {
        return accounts.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public AccountDALDTO mapToEntity(AccountBLLDTO newAccount) {

        AccountDALDTO entity = new AccountDALDTO();
        entity.setId(newAccount.getId());
        entity.setStatus(newAccount.getStatus());
//        entity.setCustomerAccounts(customerAccountBLLMapper.mapToEntity(newAccount.getCustomerAccounts()));
//        entity.setOffers(offerBLLMapper.mapToEntity(newAccount.getOffers()));

        return entity;
    }
}
