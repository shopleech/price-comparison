package com.shopleech.publicapi.bll.dto;

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
public class ShopBLLDTO {
    private Integer id;

    private String name;
    private String address;
    private String url;
    private Double latitude;
    private Double longitude;
}
