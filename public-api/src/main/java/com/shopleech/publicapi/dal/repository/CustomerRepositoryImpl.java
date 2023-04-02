package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.CustomerDALDTO;
import com.shopleech.publicapi.dal.mapper.CustomerDALMapper;
import com.shopleech.publicapi.domain.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Component
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @Autowired
    private CustomerDALMapper customerDALMapper;

    @PersistenceContext
    EntityManager em;

    @Transactional
    @Override
    public CustomerDALDTO addCustomer(CustomerDALDTO customer) {

        Session session = em.unwrap(Session.class);

        Customer customer1 = customerDALMapper.mapToEntity(customer);
        session.persist(customer1);

        return customer;
    }

    @Override
    public CustomerDALDTO getCustomerById(Integer id) {
        return null;
    }

    @Override
    public List<CustomerDALDTO> getAllCustomers() {

        TypedQuery<Customer> query = em.createQuery("select p from Customer p", Customer.class);
        List<Customer> customers = query.getResultList();

        return customerDALMapper.mapToDto(customers);
    }
}
