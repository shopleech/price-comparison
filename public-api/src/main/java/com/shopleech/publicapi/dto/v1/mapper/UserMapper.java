package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.bll.dto.UserBLLDTO;
import com.shopleech.publicapi.dto.v1.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class UserMapper {

    public List<UserDTO> mapToDto(List<UserBLLDTO> users) {
        return users.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public UserDTO mapToDto(UserBLLDTO c) {
        return new UserDTO(
                c.getFirstname(),
                c.getLastname(),
                c.getEmail()
        );
    }

    public UserBLLDTO mapToEntity(UserDTO newUser) {
        UserBLLDTO entity = new UserBLLDTO();
        entity.setFirstname(newUser.getFirstname());
        entity.setLastname(newUser.getLastname());
        entity.setEmail(newUser.getEmail());
        return entity;
    }

}
