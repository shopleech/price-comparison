package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.dal.repository.RoleRepository;
import com.shopleech.publicapi.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ahto Jalak
 * @since 25.02.2023
 */
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createNewRole(Role role) {
        return roleRepository.save(role);
    }

}
