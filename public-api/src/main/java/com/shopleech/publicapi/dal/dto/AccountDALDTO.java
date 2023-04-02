package com.shopleech.publicapi.dal.dto;

import com.shopleech.base.config.type.StatusTypeCode;
import lombok.*;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDALDTO {

    private Integer id;

    private StatusTypeCode status;

    private List<CustomerAccountDALDTO> customerAccounts;

    private List<OfferDALDTO> offers;
}
