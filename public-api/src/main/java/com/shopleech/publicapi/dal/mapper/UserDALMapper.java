package com.shopleech.publicapi.dal.mapper;

import com.shopleech.publicapi.dal.dto.UserDALDTO;
import com.shopleech.publicapi.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class UserDALMapper {

    @Autowired
    private CustomerDALMapper customerDALMapper;
//    @Autowired
//    private UserRoleDALMapper userRoleDALMapper;

    public List<UserDALDTO> mapToDto(List<User> users) {

        return users.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public UserDALDTO mapToDto(User c) {

        UserDALDTO dto = new UserDALDTO();
        dto.setId(c.getId());
        dto.setCustomer(customerDALMapper.mapToDto(c.getCustomer()));
        dto.setFirstname(c.getFirstname());
        dto.setLastname(c.getLastname());
        dto.setEmail(c.getEmail());
        dto.setPassword(c.getPassword());
        dto.setEnabled(c.isEnabled());
//        dto.setUserRoles(userRoleDALMapper.mapToDto(c.getUserRoles()));

        return dto;
    }

    public List<User> mapToEntity(List<UserDALDTO> users) {

        return users.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public User mapToEntity(UserDALDTO newUser) {

        User entity = new User();
        entity.setId(newUser.getId());
        // entity.setCustomer(customerDALMapper.mapToEntity(newUser.getCustomer()));
        entity.setFirstname(newUser.getFirstname());
        entity.setLastname(newUser.getLastname());
        entity.setEmail(newUser.getEmail());
        entity.setPassword(newUser.getPassword());
        entity.setEnabled(newUser.isEnabled());
//        entity.setUserRoles(userRoleDALMapper.mapToEntity(newUser.getUserRoles()));

        return entity;
    }

}
