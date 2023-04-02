package com.shopleech.publicapi.dal.mapper;

import com.shopleech.publicapi.dal.dto.RoleDALDTO;
import com.shopleech.publicapi.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 23.03.2023
 */
@Component
public class RoleDALMapper {

//    @Autowired
//    UserRoleDALMapper userRoleDALMapper;

    public List<RoleDALDTO> mapToDto(List<Role> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public RoleDALDTO mapToDto(Role c) {

        RoleDALDTO dto = new RoleDALDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());
        // userRoleDALMapper.mapToDto(c.getUserRoles())

        return dto;
    }

    public List<Role> mapToEntity(List<RoleDALDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Role mapToEntity(RoleDALDTO entity) {

        Role c = new Role();
        c.setId(entity.getId());
        c.setName(entity.getName());
//        c.setUserRoles(userRoleDALMapper.mapToEntity(entity.getUserRoles()));

        return c;
    }
}
