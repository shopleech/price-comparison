package com.shopleech.publicapi.bll.mapper;

import com.shopleech.publicapi.bll.dto.RoleBLLDTO;
import com.shopleech.publicapi.dal.dto.RoleDALDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 23.03.2023
 */
@Component
public class RoleBLLMapper {

//    @Autowired
//    UserRoleBLLMapper userRoleBLLMapper;

    public List<RoleBLLDTO> mapToDto(List<RoleDALDTO> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public RoleBLLDTO mapToDto(RoleDALDTO c) {
        RoleBLLDTO dto = new RoleBLLDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());
        // userRoleBLLMapper.mapToDto(c.getUserRoles())

        return dto;
    }

    public List<RoleDALDTO> mapToEntity(List<RoleBLLDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public RoleDALDTO mapToEntity(RoleBLLDTO entity) {

        RoleDALDTO c = new RoleDALDTO();
        c.setId(entity.getId());
        c.setName(entity.getName());
        // c.setUserRoles(userRoleBLLMapper.mapToEntity(entity.getUserRoles()));

        return c;
    }
}
