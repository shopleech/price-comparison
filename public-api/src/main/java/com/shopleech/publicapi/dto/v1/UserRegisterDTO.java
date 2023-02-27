package com.shopleech.publicapi.dto.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 04.02.2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
