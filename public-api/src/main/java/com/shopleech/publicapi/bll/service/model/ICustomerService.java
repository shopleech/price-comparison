package com.shopleech.publicapi.bll.service.model;

import com.shopleech.publicapi.domain.Customer;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 27.03.2023
 */
public interface ICustomerService {
    List<Customer> getAll();

    Integer remove(Integer id);

    Customer get(Integer id) throws Exception;

    Customer add(Customer customer);

    Customer update(Integer id, Customer customer);

    Customer getCustomerByUserId(Integer id) throws Exception;

    Customer getCustomerByAccountId(Integer id) throws Exception;
}
