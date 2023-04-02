package com.shopleech.publicapi.dal.dto;

import lombok.*;

/**
 * @author Ahto Jalak
 * @since 25.03.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShopDALDTO {

    private Integer id;

    private String name;
    private String address;
    private String url;
    private Double latitude;
    private Double longitude;
}
