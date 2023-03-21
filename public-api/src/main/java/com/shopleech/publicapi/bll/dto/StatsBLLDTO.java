package com.shopleech.publicapi.bll.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 22.03.2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatsBLLDTO {

    private int numOfProducts;
    private int numOfPriceUpdates;
    private int numOfUsers;
}
