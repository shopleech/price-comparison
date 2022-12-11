package com.shopleech.service.dto;

import com.shopleech.domain.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserBllConverter {
    public UserBllDto convertEntityToDto(User user) {
        ModelMapper modelMapper = new ModelMapper();
        UserBllDto userDto = modelMapper.map(user, UserBllDto.class);
        return userDto;
    }

    public User convertDtoToEntity(UserBllDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDto, User.class);
        return user;
    }
}
