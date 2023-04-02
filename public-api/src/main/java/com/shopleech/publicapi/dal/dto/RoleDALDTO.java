package com.shopleech.publicapi.dal.dto;

import lombok.*;

/**
 * @author Ahto Jalak
 * @since 25.02.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleDALDTO {

    private Integer id;

    private String name;

//    List<UserRoleDALDTO> userRoles;
}
