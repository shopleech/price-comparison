package com.shopleech.publicapi.bll.dto;

import com.shopleech.publicapi.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBLLDTO {

    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Set<Role> roles;
    private boolean enabled;
}
