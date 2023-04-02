package com.shopleech.publicapi.dal.mapper;

import com.shopleech.publicapi.dal.dto.UserRoleDALDTO;
import com.shopleech.publicapi.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 23.03.2023
 */
@Component
public class UserRoleDALMapper {

//    @Autowired
//    UserDALMapper userDALMapper;
//    @Autowired
//    RoleDALMapper roleDALMapper;

    public List<UserRoleDALDTO> mapToDto(List<UserRole> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public UserRoleDALDTO mapToDto(UserRole c) {

        UserRoleDALDTO dto = new UserRoleDALDTO();
        dto.setId(c.getId());
        // userDALMapper.mapToDto(c.getUser()),
        // roleDALMapper.mapToDto(c.getRole())

        return dto;
    }

    public List<UserRole> mapToEntity(List<UserRoleDALDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public UserRole mapToEntity(UserRoleDALDTO entity) {

        UserRole c = new UserRole();
        c.setId(entity.getId());
//        c.setUser(userDALMapper.mapToEntity(entity.getUser()));
//        c.setRole(roleDALMapper.mapToEntity(entity.getRole()));

        return c;
    }
}
