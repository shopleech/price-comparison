package com.shopleech.publicapi.bll.dto;

import com.shopleech.publicapi.domain.Role;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserBLLDTO {

    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private boolean enabled;
    private Set<Role> roles = new HashSet<>();
}
