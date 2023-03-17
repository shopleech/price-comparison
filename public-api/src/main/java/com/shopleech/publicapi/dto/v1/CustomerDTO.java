package com.shopleech.publicapi.dto.v1;

import com.shopleech.base.config.type.StatusTypeCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private Integer id;
    private String personalCode;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private StatusTypeCode status;
}
