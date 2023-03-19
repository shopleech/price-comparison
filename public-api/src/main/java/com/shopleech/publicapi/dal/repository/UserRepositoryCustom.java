package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.UserDALDTO;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 07.02.2023
 */
public interface UserRepositoryCustom {

    void addUser(UserDALDTO user);

    UserDALDTO getUserById(Integer id);

    List<UserDALDTO> getAllUsers();

    UserDALDTO getUserByUsername(String username);
}
