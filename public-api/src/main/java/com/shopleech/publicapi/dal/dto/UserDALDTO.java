package com.shopleech.publicapi.dal.dto;

import com.shopleech.publicapi.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ahto Jalak
 * @since 03.02.2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDALDTO {

    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private boolean enabled;
    private Set<Role> roles = new HashSet<>();
}
