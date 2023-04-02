package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.dto.CustomerAccountBLLDTO;
import com.shopleech.publicapi.bll.mapper.CustomerAccountBLLMapper;
import com.shopleech.publicapi.bll.mapper.CustomerBLLMapper;
import com.shopleech.publicapi.dal.repository.CustomerAccountRepository;
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
public class CustomerAccountService {

    @Autowired
    protected CustomerAccountRepository customerAccountRepository;

    @Autowired
    protected CustomerAccountBLLMapper customerAccountMapper;

    public CustomerAccountBLLDTO createCustomerAccount(CustomerAccountBLLDTO relation) {
        return customerAccountMapper.mapToDto(
                customerAccountRepository.createCustomerAccount(customerAccountMapper.mapToEntity(relation))
        );
    }
}
