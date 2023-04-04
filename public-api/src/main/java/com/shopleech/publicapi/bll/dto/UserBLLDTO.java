package com.shopleech.publicapi.bll.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserBLLDTO {
    private Integer id;

    private CustomerBLLDTO customer;

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private boolean enabled;

    private Set<UserRoleBLLDTO> userRoles = new HashSet<>();
}
