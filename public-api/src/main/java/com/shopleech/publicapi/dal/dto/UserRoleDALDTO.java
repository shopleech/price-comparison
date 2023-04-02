package com.shopleech.publicapi.dal.dto;

import lombok.*;

/**
 * @author Ahto Jalak
 * @since 23.03.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRoleDALDTO {

    private Integer id;

    private UserDALDTO user;

    private RoleDALDTO role;
}
