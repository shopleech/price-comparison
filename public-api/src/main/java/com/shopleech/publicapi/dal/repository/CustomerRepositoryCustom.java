package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.CustomerDALDTO;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 07.02.2023
 */
public interface CustomerRepositoryCustom {

    CustomerDALDTO addCustomer(CustomerDALDTO mapToEntity);

    CustomerDALDTO getCustomerById(Integer id);

    List<CustomerDALDTO> getAllCustomers();
}
