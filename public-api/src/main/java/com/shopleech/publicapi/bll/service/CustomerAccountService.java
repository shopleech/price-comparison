package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.dal.repository.CustomerAccountRepository;
import com.shopleech.publicapi.domain.CustomerAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ahto Jalak
 * @since 27.03.2023
 */
@Service
public class CustomerAccountService {
    Logger logger = LoggerFactory.getLogger(CustomerAccountService.class);

    @Autowired
    protected CustomerAccountRepository customerAccountRepository;

    public CustomerAccount add(CustomerAccount relation) {
        return customerAccountRepository.save(relation);
    }
}
