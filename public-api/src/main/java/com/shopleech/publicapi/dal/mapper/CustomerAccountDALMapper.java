package com.shopleech.publicapi.dal.mapper;

import com.shopleech.publicapi.dal.dto.CustomerAccountDALDTO;
import com.shopleech.publicapi.domain.CustomerAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 23.03.2023
 */
@Component
public class CustomerAccountDALMapper {

    @Autowired
    private CustomerDALMapper customerDALMapper;
    @Autowired
    private AccountDALMapper accountDALMapper;

    public List<CustomerAccountDALDTO> mapToDto(List<CustomerAccount> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public CustomerAccountDALDTO mapToDto(CustomerAccount c) {

        CustomerAccountDALDTO dto = new CustomerAccountDALDTO();
        dto.setId(c.getId());
        dto.setCustomer(customerDALMapper.mapToDto(c.getCustomer()));
        dto.setAccount(accountDALMapper.mapToDto(c.getAccount()));

        return dto;
    }

    public List<CustomerAccount> mapToEntity(List<CustomerAccountDALDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public CustomerAccount mapToEntity(CustomerAccountDALDTO entity) {

        CustomerAccount c = new CustomerAccount();
        c.setId(entity.getId());
        c.setCustomer(customerDALMapper.mapToEntity(entity.getCustomer()));
        c.setAccount(accountDALMapper.mapToEntity(entity.getAccount()));

        return c;
    }
}
