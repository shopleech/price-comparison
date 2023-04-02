package com.shopleech.publicapi.dto.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 22.03.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsDTO {
    private int numOfProducts;
    private int numOfPriceUpdates;
    private int numOfUsers;
}
