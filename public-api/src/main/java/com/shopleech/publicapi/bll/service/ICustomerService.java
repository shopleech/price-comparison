package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.dto.CustomerBLLDTO;

/**
 * @author Ahto Jalak
 * @since 27.03.2023
 */
public interface ICustomerService {
    CustomerBLLDTO createCustomer(CustomerBLLDTO customer);
}
