package com.shopleech.service;

import com.shopleech.domain.User;
import com.shopleech.service.dto.UserBllDto;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    UserBllDto getUserById(UUID id);

    List<UserBllDto> getUsers();

    void deleteUser(UUID id);

    UserBllDto addUser(UserBllDto user);

    User updateUser(User user, UUID id);
}
