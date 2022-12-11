package com.shopleech.dto;

import com.shopleech.service.dto.UserBllDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserDto convertEntityToDto(UserBllDto user) {
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    public UserBllDto convertDtoToEntity(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        UserBllDto user = modelMapper.map(userDto, UserBllDto.class);
        return user;
    }
}
