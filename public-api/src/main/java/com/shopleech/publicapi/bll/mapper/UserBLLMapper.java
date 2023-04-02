package com.shopleech.publicapi.bll.mapper;

import com.shopleech.publicapi.bll.dto.UserBLLDTO;
import com.shopleech.publicapi.dal.dto.UserDALDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class UserBLLMapper {

    @Autowired
    CustomerBLLMapper customerBLLMapper;
//    @Autowired
//    UserRoleBLLMapper userRoleBLLMapper;

    public List<UserBLLDTO> mapToDto(List<UserDALDTO> users) {

        return users.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public UserBLLDTO mapToDto(UserDALDTO c) {

        if (c == null) {
            return null;
        }

        UserBLLDTO dto = new UserBLLDTO();
        dto.setId(c.getId());
        if (c.getCustomer() != null) {
            dto.setCustomer(customerBLLMapper.mapToDto(c.getCustomer()));
        }
        dto.setFirstname(c.getFirstname());
        dto.setLastname(c.getLastname());
        dto.setEmail(c.getEmail());
        dto.setPassword(c.getPassword());
        dto.setEnabled(c.isEnabled());
//        dto.setUserRoles(userRoleBLLMapper.mapToDto(c.getUserRoles()));

        return dto;
    }

    public List<UserDALDTO> mapToEntity(List<UserBLLDTO> users) {

        return users.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public UserDALDTO mapToEntity(UserBLLDTO newUser) {

        if (newUser == null) return null;

        UserDALDTO entity = new UserDALDTO();
        entity.setId(newUser.getId());
        if (newUser.getCustomer() != null) {
            entity.setCustomer(customerBLLMapper.mapToEntity(newUser.getCustomer()));
        }
        entity.setFirstname(newUser.getFirstname());
        entity.setLastname(newUser.getLastname());
        entity.setEmail(newUser.getEmail());
        entity.setPassword(newUser.getPassword());
        entity.setEnabled(newUser.isEnabled());
        // entity.setUserRoles(userRoleBLLMapper.mapToEntity(newUser.getUserRoles()));

        return entity;
    }

}
