package com.shopleech.publicapi.dto.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 05.04.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private Integer id;

    private String name;
}
