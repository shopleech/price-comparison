package com.shopleech.publicapi.dal.dto;

import com.shopleech.base.config.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 03.02.2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

   private Integer id;

   private String firstname;

   private String lastname;

   private String email;

   private String password;

   private Role role;
}
