package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.dto.CustomerBLLDTO;
import com.shopleech.publicapi.bll.mapper.CustomerBLLMapper;
import com.shopleech.publicapi.bll.service.model.ICustomerService;
import com.shopleech.publicapi.dal.mapper.CustomerDALMapper;
import com.shopleech.publicapi.dal.repository.CustomerRepository;
import com.shopleech.publicapi.dal.repository.UserRepository;
import com.shopleech.publicapi.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    protected UserRepository userRepository;
    @Autowired
    protected CustomerBLLMapper customerMapper;
    @Autowired
    protected CustomerDALMapper customerDALMapper;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Integer remove(Integer id) {
        return id;
    }

    @Override
    public Customer get(Integer id) throws Exception {
        var item = customerRepository.findById(id);

        if (item.isEmpty()) {
            throw new Exception("customer not found");
        }

        return item.get();
    }

    @Override
    public Customer add(Customer customer) {
        return customer;
    }

    @Override
    public Customer update(Integer id, Customer customer) {
        return customer;
    }

    @Override
    public Customer getCustomerByUserId(Integer id) throws Exception {
        var user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new Exception("user not found");
        }

        return get(user.get().getCustomer().getId());
    }
}
