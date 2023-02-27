package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.CustomerDALDTO;
import com.shopleech.publicapi.dal.mapper.CustomerDALMapper;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    private CustomerRepository customerRepository;

    private CustomerDALMapper customerDALMapper;

    @Override
    public void addCustomer(CustomerDALDTO customer) {
        // customerRepository.add(customerDALMapper.mapToEntity(customer));
    }

    @Override
    public CustomerDALDTO getCustomerById(Integer id) {
        return customerDALMapper.mapToDto(customerRepository.getReferenceById(id));
    }

    @Override
    public List<CustomerDALDTO> getAllCustomers() {
        return customerDALMapper.mapToDto(customerRepository.findAll());
    }
}
