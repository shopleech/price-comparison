package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.dto.CustomerBLLDTO;
import com.shopleech.publicapi.bll.mapper.CustomerBLLMapper;
import com.shopleech.publicapi.dal.mapper.CustomerDALMapper;
import com.shopleech.publicapi.dal.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ahto Jalak
 * @since 27.03.2023
 */
@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    @Autowired
    protected CustomerRepository customerRepository;

    @Autowired
    protected CustomerBLLMapper customerMapper;

    @Autowired
    protected CustomerDALMapper customerDALMapper;

    @Override
    public CustomerBLLDTO createCustomer(CustomerBLLDTO customer) {
        return customerMapper.mapToDto(
                customerDALMapper.mapToDto(
                        customerRepository.save(
                                customerDALMapper.mapToEntity(
                                        customerMapper.mapToEntity(customer)
                                )
                        )
                )
        );
    }
}
