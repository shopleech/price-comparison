package com.shopleech.publicapi.bll.service.model;

import com.shopleech.publicapi.domain.Role;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 05.04.2023
 */
public interface IRoleService {
    List<Role> getAll();

    Role get(Integer id) throws Exception;

    Role add(Role role);

    Role update(Integer id, Role role);

    Integer remove(Integer id);
}
