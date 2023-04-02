package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.RoleDALDTO;
import com.shopleech.publicapi.dal.dto.UserDALDTO;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 07.02.2023
 */
public interface UserRepositoryCustom {

    UserDALDTO addUser(UserDALDTO user);

    UserDALDTO getUserById(Integer id);

    List<UserDALDTO> getAllUsers();

    int getUserCount();

    RoleDALDTO getRole(String user);

    UserDALDTO updateUser(UserDALDTO mapToEntity);
}
