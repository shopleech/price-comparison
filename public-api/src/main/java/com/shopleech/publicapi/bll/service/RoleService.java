package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.service.model.IRoleService;
import com.shopleech.publicapi.dal.repository.RoleRepository;
import com.shopleech.publicapi.domain.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 25.02.2023
 */
@Service
public class RoleService implements IRoleService {
    Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role get(Integer id) throws Exception {
        var item = roleRepository.findById(id);

        if (item.isEmpty()) {
            throw new Exception("role not found");
        }

        return item.get();
    }

    @Override
    public Role add(Role role) {
        return role;
    }

    @Override
    public Role update(Integer id, Role role) {
        return role;
    }

    @Override
    public Integer remove(Integer id) {
        return id;
    }
}
