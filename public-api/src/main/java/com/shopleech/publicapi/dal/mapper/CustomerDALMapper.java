package com.shopleech.publicapi.dal.mapper;

import com.shopleech.publicapi.dal.dto.CustomerDALDTO;
import com.shopleech.publicapi.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class CustomerDALMapper {

//    @Autowired
//    private CustomerAccountDALMapper customerAccountDALMapper;
//    @Autowired
//    private UserDALMapper userDALMapper;
//    @Autowired
//    private WatchlistDALMapper watchlistDALMapper;
//    @Autowired
//    private AlarmDALMapper alarmDALMapper;
//    @Autowired
//    private ReviewDALMapper reviewDALMapper;

    public List<CustomerDALDTO> mapToDto(List<Customer> customers) {
        return customers.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public CustomerDALDTO mapToDto(Customer c1) {

        if (c1 == null) return null;

        CustomerDALDTO dto = new CustomerDALDTO();
        dto.setId(c1.getId());
        dto.setPersonalCode(c1.getPersonalCode());
        dto.setFirstName(c1.getFirstName());
        dto.setLastName(c1.getLastName());
        dto.setEmail(c1.getEmail());
        dto.setPhoneNumber(c1.getPhoneNumber());
        dto.setStatus(c1.getStatus());
//        dto.setUser(userDALMapper.mapToDto(c1.getUser()));
//        dto.setCustomerAccounts(customerAccountDALMapper.mapToDto(c1.getCustomerAccounts()));
//        dto.setWatchlists(watchlistDALMapper.mapToDto(c1.getWatchlists()));
//        dto.setAlarms(alarmDALMapper.mapToDto(c1.getAlarms()));
//        dto.setReviews(reviewDALMapper.mapToDto(c1.getReviews()));

        return dto;
    }

    public List<Customer> mapToEntity(List<CustomerDALDTO> customers) {
        return customers.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Customer mapToEntity(CustomerDALDTO entity6) {

        if (entity6 == null) return null;

        Customer c = new Customer();
        c.setId(entity6.getId());
        c.setPersonalCode(entity6.getPersonalCode());
        c.setFirstName(entity6.getFirstName());
        c.setLastName(entity6.getLastName());
        c.setEmail(entity6.getEmail());
        c.setPhoneNumber(entity6.getPhoneNumber());
        c.setStatus(entity6.getStatus());
//        c.setUser(userDALMapper.mapToEntity(entity.getUser()));
//        c.setCustomerAccounts(customerAccountDALMapper.mapToEntity(entity.getCustomerAccounts()));
//        c.setWatchlists(watchlistDALMapper.mapToEntity(entity.getWatchlists()));
//        c.setAlarms(alarmDALMapper.mapToEntity(entity.getAlarms()));
//        c.setReviews(reviewDALMapper.mapToEntity(entity.getReviews()));

        return c;
    }

}
