package com.shopleech.publicapi.dal.dto;

import com.shopleech.base.config.type.StatusTypeCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 07.02.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDALDTO {

    private Integer id;
    private String personalCode;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private StatusTypeCode status;
}
