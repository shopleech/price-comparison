package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.domain.Role;
import com.shopleech.publicapi.dto.v1.RoleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 05.04.2023
 */
@Component
public class RoleMapper {
    Logger logger = LoggerFactory.getLogger(RoleMapper.class);

    public List<RoleDTO> mapToDto(List<Role> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public RoleDTO mapToDto(Role c) {
        RoleDTO dto = new RoleDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());

        return dto;
    }

    public List<Role> mapToEntity(List<RoleDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Role mapToEntity(RoleDTO entity) {
        Role c = new Role();
        c.setId(entity.getId());
        c.setName(entity.getName());

        return c;
    }
}
