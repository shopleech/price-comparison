package com.shopleech.publicapi.bll.dto;

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
public class AccountBLLDTO {

    private Integer id;
    private StatusTypeCode status;
}
