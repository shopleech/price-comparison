package com.shopleech.publicapi.bll.mapper;

import com.shopleech.publicapi.bll.dto.CustomerBLLDTO;
import com.shopleech.publicapi.dal.dto.CustomerDALDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 23.03.2023
 */
@Component
public class CustomerBLLMapper {

//    @Autowired
//    CustomerAccountBLLMapper customerAccountBLLMapper;
//    @Autowired
//    UserBLLMapper userBLLMapper;
//    @Autowired
//    WatchlistBLLMapper watchlistBLLMapper;
//    @Autowired
//    AlarmBLLMapper alarmBLLMapper;
//    @Autowired
//    ReviewBLLMapper reviewBLLMapper;

    public List<CustomerBLLDTO> mapToDto(List<CustomerDALDTO> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public CustomerBLLDTO mapToDto(CustomerDALDTO c2) {

        if (c2 == null) return null;

        CustomerBLLDTO dto = new CustomerBLLDTO();
        dto.setId(c2.getId());
        dto.setPersonalCode(c2.getPersonalCode());
        dto.setFirstName(c2.getFirstName());
        dto.setLastName(c2.getLastName());
        dto.setEmail(c2.getEmail());
        dto.setPhoneNumber(c2.getPhoneNumber());
        dto.setStatus(c2.getStatus());
//        dto.setUser(userBLLMapper.mapToDto(c2.getUser()));
//        dto.setCustomerAccounts(customerAccountBLLMapper.mapToDto(c2.getCustomerAccounts()));
//        dto.setWatchlists(watchlistBLLMapper.mapToDto(c2.getWatchlists()));
//        dto.setAlarms(alarmBLLMapper.mapToDto(c2.getAlarms()));
//        dto.setReviews(reviewBLLMapper.mapToDto(c2.getReviews()));

        return dto;
    }

    public List<CustomerDALDTO> mapToEntity(List<CustomerBLLDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public CustomerDALDTO mapToEntity(CustomerBLLDTO entity3) {

        if (entity3 == null) {
            return null;
        }

        CustomerDALDTO c = new CustomerDALDTO();
        c.setId(entity3.getId());
        c.setPersonalCode(entity3.getPersonalCode());
        c.setFirstName(entity3.getFirstName());
        c.setLastName(entity3.getLastName());
        c.setEmail(entity3.getEmail());
        c.setPhoneNumber(entity3.getPhoneNumber());
        c.setStatus(entity3.getStatus());
//        c.setUser(userBLLMapper.mapToEntity(entity.getUser()));
//        c.setCustomerAccounts(customerAccountBLLMapper.mapToEntity(entity.getCustomerAccounts()));
//        c.setWatchlists(watchlistBLLMapper.mapToEntity(entity.getWatchlists()));
//        c.setAlarms(alarmBLLMapper.mapToEntity(entity.getAlarms()));
//        c.setReviews(reviewBLLMapper.mapToEntity(entity.getReviews()));

        return c;
    }
}
