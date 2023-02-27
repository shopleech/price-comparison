package com.shopleech.publicapi.dal.mapper;

import com.shopleech.publicapi.dal.dto.CustomerDALDTO;
import com.shopleech.publicapi.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class CustomerDALMapper {

    public List<CustomerDALDTO> mapToDto(List<Customer> customers) {
        return customers.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public CustomerDALDTO mapToDto(Customer c) {
        return new CustomerDALDTO(
                c.getId(),
                c.getPersonalCode(),
                c.getFirstName(),
                c.getLastName(),
                c.getEmail(),
                c.getPhoneNumber(),
                c.getStatus()
        );
    }

    public Customer mapToEntity(CustomerDALDTO newCustomer) {
        Customer entity = new Customer();
        entity.setId(newCustomer.getId());
        entity.setPersonalCode(newCustomer.getPersonalCode());
        entity.setFirstName(newCustomer.getFirstName());
        entity.setLastName(newCustomer.getLastName());
        entity.setEmail(newCustomer.getEmail());
        entity.setPhoneNumber(newCustomer.getPhoneNumber());
        entity.setStatus(newCustomer.getStatus());
        return entity;
    }

}
