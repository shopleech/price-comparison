package com.shopleech.publicapi.dal.dto;

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
public class CustomerAccountDALDTO {

    private Integer id;

    private CustomerDALDTO customer;

    private AccountDALDTO account;
}
