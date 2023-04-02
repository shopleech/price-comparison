package com.shopleech.publicapi.bll.mapper;

import com.shopleech.publicapi.bll.dto.UserRoleBLLDTO;
import com.shopleech.publicapi.dal.dto.UserRoleDALDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 23.03.2023
 */
@Component
public class UserRoleBLLMapper {

//    @Autowired
//    UserBLLMapper userBLLMapper;
//    @Autowired
//    RoleBLLMapper roleBLLMapper;

    public List<UserRoleBLLDTO> mapToDto(List<UserRoleDALDTO> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public UserRoleBLLDTO mapToDto(UserRoleDALDTO c) {

        UserRoleBLLDTO dto = new UserRoleBLLDTO();
        dto.setId(c.getId());
        // userBLLMapper.mapToDto(c.getUser()),
        // roleBLLMapper.mapToDto(c.getRole())

        return dto;
    }

    public List<UserRoleDALDTO> mapToEntity(List<UserRoleBLLDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public UserRoleDALDTO mapToEntity(UserRoleBLLDTO entity) {

        UserRoleDALDTO c = new UserRoleDALDTO();
        c.setId(entity.getId());
//        c.setUser(userBLLMapper.mapToEntity(entity.getUser()));
//        c.setRole(roleBLLMapper.mapToEntity(entity.getRole()));

        return c;
    }
}
