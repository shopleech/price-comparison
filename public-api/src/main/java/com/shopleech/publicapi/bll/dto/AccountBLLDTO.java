package com.shopleech.publicapi.bll.dto;

import com.shopleech.base.config.type.StatusTypeCode;
import lombok.*;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountBLLDTO {
    public Integer id;

    public StatusTypeCode status;

//    public List<CustomerAccountBLLDTO> customerAccounts;
//
//    public List<OfferBLLDTO> offers;
}
