package com.shopleech.publicapi.dal.dto;

import lombok.*;

import java.util.Set;

/**
 * @author Ahto Jalak
 * @since 03.02.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDALDTO {

    private Integer id;

    private CustomerDALDTO customer;

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private boolean enabled;

    // private Set<UserRoleDALDTO> userRoles;
}
