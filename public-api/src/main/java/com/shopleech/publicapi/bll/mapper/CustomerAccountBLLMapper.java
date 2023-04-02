package com.shopleech.publicapi.bll.mapper;

import com.shopleech.publicapi.bll.dto.CustomerAccountBLLDTO;
import com.shopleech.publicapi.dal.dto.CustomerAccountDALDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 23.03.2023
 */
@Component
public class CustomerAccountBLLMapper {

    @Autowired
    CustomerBLLMapper customerBLLMapper;
    @Autowired
    AccountBLLMapper accountBLLMapper;

    public List<CustomerAccountBLLDTO> mapToDto(List<CustomerAccountDALDTO> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public CustomerAccountBLLDTO mapToDto(CustomerAccountDALDTO c) {

        CustomerAccountBLLDTO dto = new CustomerAccountBLLDTO();
        dto.setId(c.getId());
        dto.setCustomer(customerBLLMapper.mapToDto(c.getCustomer()));
        dto.setAccount(accountBLLMapper.mapToDto(c.getAccount()));

        return dto;
    }

    public List<CustomerAccountDALDTO> mapToEntity(List<CustomerAccountBLLDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }


    public CustomerAccountDALDTO mapToEntity(CustomerAccountBLLDTO entity) {

        CustomerAccountDALDTO c = new CustomerAccountDALDTO();
        c.setId(entity.getId());
        // c.setCustomer(customerBLLMapper.mapToEntity(entity.getCustomer()));
        // c.setAccount(accountBLLMapper.mapToEntity(entity.getAccount()));

        return c;
    }
}
