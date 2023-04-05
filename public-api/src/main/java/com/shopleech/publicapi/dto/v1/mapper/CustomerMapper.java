package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.domain.Customer;
import com.shopleech.publicapi.dto.v1.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 05.04.2023
 */
@Component
public class CustomerMapper {
    Logger logger = LoggerFactory.getLogger(CustomerMapper.class);

    public List<CustomerDTO> mapToDto(List<Customer> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public CustomerDTO mapToDto(Customer c) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(c.getId());
        // dto.setParentCustomer();
        // dto.setName(c.getName());
        // dto.setCustomerTypeCode();

        return dto;
    }

    public List<Customer> mapToEntity(List<CustomerDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Customer mapToEntity(CustomerDTO entity) {

        Customer c = new Customer();
        c.setId(entity.getId());
        // c.setParentCustomer();
        // c.setName(entity.getName());
        // c.setCustomerTypeCode();

        return c;
    }
}
