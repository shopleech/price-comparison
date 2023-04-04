package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.service.RoleService;
import com.shopleech.publicapi.domain.Role;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ahto Jalak
 * @since 25.02.2023
 */
@RestController
@RequestMapping("/v1/role")
@RequiredArgsConstructor
@Tag(name = "Role controller", description = "Endpoint")
public class RoleController {
    Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @PostMapping
    public Role createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);
    }
}
