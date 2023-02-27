package com.shopleech.publicapi.bll.mapper;

import com.shopleech.publicapi.bll.dto.UserBLLDTO;
import com.shopleech.publicapi.dal.dto.UserDALDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class UserBLLMapper {

    public List<UserBLLDTO> mapToDto(List<UserDALDTO> users) {
        return users.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public UserBLLDTO mapToDto(UserDALDTO c) {
        return new UserBLLDTO(
                c.getId(),
                c.getFirstname(),
                c.getLastname(),
                c.getEmail(),
                c.getPassword(),
                c.getRoles(),
                c.isEnabled()
        );
    }

    public UserDALDTO mapToEntity(UserBLLDTO newUser) {
        UserDALDTO entity = new UserDALDTO();
        entity.setId(newUser.getId());
        entity.setFirstname(newUser.getFirstname());
        entity.setLastname(newUser.getLastname());
        entity.setEmail(newUser.getEmail());
        entity.setPassword(newUser.getPassword());
        entity.setRoles(newUser.getRoles());
        entity.setEnabled(newUser.isEnabled());
        return entity;
    }

}
