package com.shopleech.publicapi.dal.dto;

import com.shopleech.base.config.Status;
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
public class AccountDALDTO {

    private Integer id;
    private Status status;
}