package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.domain.User;
import com.shopleech.publicapi.dto.v1.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class UserMapper {
    Logger logger = LoggerFactory.getLogger(UserMapper.class);

    public List<UserDTO> mapToDto(List<User> users) {
        return users.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public UserDTO mapToDto(User c) {
        return new UserDTO(
                c.getId(),
                c.getFirstname(),
                c.getLastname(),
                c.getEmail()
        );
    }

    public User mapToEntity(UserDTO newUser) {
        User entity = new User();
        entity.setFirstname(newUser.getFirstname());
        entity.setLastname(newUser.getLastname());
        entity.setEmail(newUser.getEmail());

        return entity;
    }

}
