package com.shopleech.publicapi.bll.dto;

import lombok.*;

/**
 * @author Ahto Jalak
 * @since 23.03.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerAccountBLLDTO {

    private Integer id;

    private CustomerBLLDTO customer;

    private AccountBLLDTO account;
}
