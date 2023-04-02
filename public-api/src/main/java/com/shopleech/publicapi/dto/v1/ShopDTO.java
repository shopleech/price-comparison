package com.shopleech.publicapi.dto.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 02.04.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopDTO {
    Integer id;
    String name;
    String address;
    String url;
    Double latitude;
    Double longitude;
}
