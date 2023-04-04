package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.dto.CustomerBLLDTO;
import com.shopleech.publicapi.bll.mapper.CustomerBLLMapper;
import com.shopleech.publicapi.dal.mapper.CustomerDALMapper;
import com.shopleech.publicapi.dal.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ahto Jalak
 * @since 27.03.2023
 */
@Service
public class CustomerService implements ICustomerService {
    Logger logger = LoggerFactory.getLogger(CustomerService.class);

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
