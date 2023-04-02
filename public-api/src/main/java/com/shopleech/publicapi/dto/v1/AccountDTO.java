package com.shopleech.publicapi.dto.v1;

import com.shopleech.base.config.type.StatusTypeCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private Integer id;
    private StatusTypeCode status;
}
