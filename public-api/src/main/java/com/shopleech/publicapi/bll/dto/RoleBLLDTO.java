package com.shopleech.publicapi.bll.dto;

import lombok.*;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 23.03.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleBLLDTO {
    private Integer id;

    private String name;

    List<UserRoleBLLDTO> userRoles;
}
