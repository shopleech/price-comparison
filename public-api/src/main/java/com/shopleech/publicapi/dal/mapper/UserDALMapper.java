package com.shopleech.publicapi.dal.mapper;

import com.shopleech.publicapi.dal.dto.UserDALDTO;
import com.shopleech.publicapi.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class UserDALMapper {

    public List<UserDALDTO> mapToDto(List<User> users) {
        return users.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public UserDALDTO mapToDto(User c) {
        return new UserDALDTO(
                c.getId(),
                c.getFirstname(),
                c.getLastname(),
                c.getEmail(),
                c.getPassword(),
                c.isEnabled(),
                c.getRoles()
        );
    }

    public User mapToEntity(UserDALDTO newUser) {
        User entity = new User();
        entity.setId(newUser.getId());
        entity.setFirstname(newUser.getFirstname());
        entity.setLastname(newUser.getLastname());
        entity.setEmail(newUser.getEmail());
        entity.setPassword(newUser.getPassword());
        return entity;
    }

}
