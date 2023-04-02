package com.shopleech.publicapi.bll.dto;

import lombok.*;

/**
 * @author Ahto Jalak
 * @since 23.03.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRoleBLLDTO {

    private Integer id;

    private UserBLLDTO user;

    private RoleBLLDTO role;
}
