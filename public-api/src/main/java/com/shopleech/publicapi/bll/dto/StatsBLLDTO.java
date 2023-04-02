package com.shopleech.publicapi.bll.dto;

import lombok.*;

/**
 * @author Ahto Jalak
 * @since 22.03.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StatsBLLDTO {

    private int numOfProducts;
    private int numOfPriceUpdates;
    private int numOfUsers;
}
